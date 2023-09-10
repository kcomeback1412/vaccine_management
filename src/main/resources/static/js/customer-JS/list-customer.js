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

	let checkbox = document.querySelector('.myCheck');
        let button = document.querySelector('#myButton');

        checkbox.addEventListener('change', function() {
            if(this.checked) {
                button.disabled = false;
            } else {
                button.disabled = true;
            }
        });


function searchForm(){
  let search = document.querySelector("#searchInvalid");
  var name=document.mySearchForm.searchId.value;  
   if (name == "") {
    search.style.setProperty("opacity", 1);
    return false;
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

      
        