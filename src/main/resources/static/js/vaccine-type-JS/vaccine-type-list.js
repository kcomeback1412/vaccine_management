const selectElement = document.querySelector('select');

$('select').on('change', () => {
 	var selectedOption = $('select option:selected').get(0);

    window.location.href = 'vaccine-type-list?pageSize=' + selectedOption.value;
});

$('.btn-update').on('click', () => {
 	var selectedCheckboxes = $('input[type="checkbox"]:checked');

    if(selectedCheckboxes.length == 0) {
		$('#formMsg').text('No data to make inactive!');
	}
	else {
		$('#formMsg').text('');
		$('#comfirmModal').modal('show');
	}
});

$('#confirmBtn').on('click', () => {
	var request = new XMLHttpRequest();
	
	var method = "POST";
	var url = "/update-vaccine-type-list";
	
	request.open(method, url, true);
	
	request.send();
});
