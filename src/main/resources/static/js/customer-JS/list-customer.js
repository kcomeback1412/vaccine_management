function checkUncheck(main){
	all = document.getElementsByName('id');
	for(var a = 0; a < all.length ; a ++){
			all[a].checked = main.checked;
	}
}

function checkUncheckId(main){
	all = document.getElementsByName('id');
		if(main.checked == false){
			document.getElementById("checkboxId").checked = false;
		}
}

function updateCheck(){
	var a = document.getElementsByName('id');
	var newvar = 0;
	var count;
	
	for(count = 0 ; count < a.length; count ++){
		if(a[count].checked == true){
			newvar = newvar + 1;
		}
	}
	
	if(newvar > 1){
		alert("You can update only 1 row!");
	}else if(newvar < 1){
		alert("You must select a row to update!");
		return false;
	}
}

function deleteCheck(){
	var a = document.getElementsByName('id');
	var newvar = 0;
	var count;
	
	for(count = 0 ; count < a.length; count ++){
		if(a[count].checked == true){
			newvar = newvar + 1;
		}
	}
	
	if(newvar > 0){
		return confirm("Are you sure to delete?");
	}else if(newvar < 1){
		alert("No data delete!");
		return false;
	}
}

$('#myButton').prop("disabled", true);
$('#myButton2').prop("disabled", true);
$('input:checkbox').click(function() {
 if ($(this).is(':checked')) {
 $('#myButton').prop("disabled", false);
 $('#myButton2').prop("disabled", false);
 } else {
 if ($('.checks').filter(':checked').length < 1){
 $('#myButton').attr('disabled',true);
 $('#myButton2').attr('disabled',true);}
 }
});


function searchForm(){
  var name=document.mySearchForm.searchId.value;  
   if (name == "") {
    return true;
  }
}        

function listForm() {
  let searchNumber = document.querySelector("#searchListInvalid");
  let name = document.myListForm.searchList.value;
  let numberRegex = /^\d+$/;
  if (name < 0) {
    alert("You must input positive number!");
    return false;
  }else if(!name.match(numberRegex)){
	searchNumber.style.setProperty("opacity", 1);
    return false;
  }
}

var tbl = document.getElementById('x');
if (tbl.rows.length == 0) {
   // empty
}

      
        