package com.spring.controller.api;

import com.spring.entities.UserDetail;
import com.spring.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CustomerRestController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping("/get-customer/{id}")
    public UserDetail detail(@PathVariable Integer id ) {
        return userDetailsService.findById(id).orElse(null);
    }
}
