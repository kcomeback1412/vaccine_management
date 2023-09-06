package com.spring.controller;

import com.spring.consts.RoleEnum;

import com.spring.entities.*;
import com.spring.repositories.UserDetailRepository;
import com.spring.service.UserDetailsService;
import com.spring.service.UsersService;
import com.spring.utils.ConvertName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional(rollbackFor = {Exception.class, Throwable.class})
//@RequestMapping("/employee-management/")
public class EmployeeController {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UsersService usersService;

    @Autowired
    UserDetailRepository userDetailRepository;

    @GetMapping("/create-employee")
    String addEmployeeUI() {
        return "employee/create-employee";
    }

    //    , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    @PostMapping(value = "/create-employee")
    public String createEmployee(
            @ModelAttribute("employeeToCreate") UserDetail userDetail,
//            @RequestParam("image") MultipartFile multipartFile,
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            RedirectAttributes redirectAttributes
    ) throws IOException {
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        Users user = new Users();
        user.setUserName(username);
        user.setPassword(password);
        user.setRoleEnum(RoleEnum.ROLE_EMPLOYEE);
        usersService.save(user);

        String name = ConvertName.replaceAllSpace(userDetail.getFullName());
        userDetail.setFullName(name);
        userDetail.setCode(ConvertName.convertNameToCode(name) + user.getUsersId());
//        userDetail.setImage(fileName);
        userDetail.setUsers2(user);
        userDetailsService.save(userDetail);

//        String uploadDir = "user-images/" + userDetail.getId();
//        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        redirectAttributes.
                addFlashAttribute("msgSuccess", "Create employee success!");
        return "redirect:/employee-list";
    }

    //    Delete employee
    @PostMapping(value = "/delete-update-employee", params = "delete")
    public String deleteEmployee(
            @RequestParam(value = "listId", required = false) List<Integer> listId,
            RedirectAttributes redirectAttributes
    ) {
        if (listId != null) {
            userDetailsService.deleteEmployee(listId);
            redirectAttributes.
                    addFlashAttribute("msgSuccess", "Delete employee success!");
        } else {
            redirectAttributes.
                    addFlashAttribute("msgError", "You must select employee to delete");
        }
        return "redirect:/employee-list";
    }

    //    Update employee UI
    @PostMapping(value = "/delete-update-employee", params = "update")
    public String updateEmployeeUI(
            @RequestParam(value = "listId", required = false) List<Integer> listId,
            RedirectAttributes redirectAttributes,
            Model model
    ) {
        if ((listId != null) && (listId.size() > 1)) {
            redirectAttributes.
                    addFlashAttribute("msgError", "You can only select 1 employee to update");
            return "redirect:/employee-list";
        } else if ((listId != null) && (listId.size() == 1)) {
            model.addAttribute("userDetailInfo", userDetailsService.findById(listId.get(0)).orElse(null));

            return "employee/update-employee";
        } else {
            redirectAttributes.
                    addFlashAttribute("msgError", "You must select employee to update");
            return "redirect:/employee-list";
        }

    }

//  Save update employee
    @PostMapping(value = "/delete-update-employee", params = "update-save")
    public String updateEmployee(
            @ModelAttribute("employeeUpdate") UserDetail userDetail,
            RedirectAttributes redirectAttributes
    ) {
        String name = ConvertName.replaceAllSpace(userDetail.getFullName());
        userDetail.setFullName(name);
        userDetail.setCode(ConvertName.convertNameToCode(name) + userDetail.getUsers2().getUsersId());

//        userDetailsService.save(userDetail);
        userDetailRepository.save(userDetail);
        redirectAttributes.addFlashAttribute("msgSuccess", "Update employee success");
        return "redirect:/employee-list";
    }

    @GetMapping("/employee-list")
    public String employeeListUI(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "nameForSearch",required = false) String nameForSearch,
            Model model
    ) {
        Integer totalEmployee = userDetailsService.countAllEmployee();
        model.addAttribute("totalEmployee", totalEmployee);
        model.addAttribute("pageSize", pageSize);

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        List<UserDetail> listEmployee;
        if( nameForSearch == null || nameForSearch.isEmpty()) {
            listEmployee = userDetailsService.findAllEmployee();
        } else  {
            listEmployee = userDetailsService.findAllByFullName(nameForSearch);
            model.addAttribute("nameForSearch", nameForSearch);
            model.addAttribute("totalEmployee", listEmployee.size());
        }
        Page<UserDetail> userDetails;
        if(listEmployee != null) {
            userDetails = userDetailsService.convertListUserDetailToPageUserDetail(pageable, listEmployee);
        } else {
            userDetails = null;
        }

        if (pageNum != null) {
            model.addAttribute("currentPage", pageNum);
        } else {
            model.addAttribute("currentPage", 0);
        }

        if (userDetails != null) {
            model.addAttribute("userDetails", userDetails);
        }
        return "employee/employee-list";
    }
}
