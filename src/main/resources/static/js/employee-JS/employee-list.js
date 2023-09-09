
function checkAndUncheckAll(source){
    let checkboxes = document.getElementsByName("listId");
    for (let i = 0; i < checkboxes.length ; i++) {
        checkboxes[i].checked = source.checked;
    }
}

function uncheckAll(source) {
    let checkboxAll = document.querySelector(".checkboxAll");
    if(source.checked == false) {
        checkboxAll.checked = false;
    }
}

document.querySelector("#page-size-list").addEventListener("change", function (){
    document.querySelector("#page-size-option").submit();
})