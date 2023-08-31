function checkUncheck(main){
	all = document.getElementsByName('id');
	for(var a = 0; a < all.length ; a ++){
		all[a].checked = main.checked;
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
	}
	
}