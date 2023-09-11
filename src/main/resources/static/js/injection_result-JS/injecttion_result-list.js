function checkAndUncheckAll(source){
    let checkboxes = document.getElementsByName("listId");
    for (let i = 0; i < checkboxes.length ; i++) {
        checkboxes[i].checked = source.checked;
    }
}

function uncheckAll(source) {
    let checkAll = true;
    let checkboxAll = document.querySelector(".checkboxAll");
    let checkboxes = document.getElementsByName("listId");
    if(source.checked == false) {
        checkboxAll.checked = false;
    } else {
        for (let i = 0; i < checkboxes.length; i++) {
            if(checkboxes[i].checked == false) {
                checkAll = false;
            }
        }
        if(checkAll) {
            checkboxAll.checked = true;
        }
    }

}

document.querySelector("#page-size-list").addEventListener("change", function (){
    document.querySelector("#page-size-option").submit();
})