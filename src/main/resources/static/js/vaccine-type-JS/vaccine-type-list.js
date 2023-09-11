const selectElement = document.querySelector('select');

var checkboxes = $('tbody input[type="checkbox"]');

$('select').on('change', () => {
 	var selectedOption = $('select option:selected').get(0);

    window.location.href = '/vaccineType-management/vaccineType-list?pageSize=' + selectedOption.value;
});

$('.btn-success').on('click', () => {	
	var selectedCheckboxes = $('input[type="checkbox"]:checked');
	
    if(selectedCheckboxes.length == 0) {
		$('#formMsg').text('No data to make active!');
	}
	else {
		$('#formMsg').text('');
		$('#comfirmModal').modal('show');
		$('#modalMsg').text('Are you sure to make active?');
		$('button[name="modalSubmit"]').val('makeActive');
	}
});

$('.btn-warning').on('click', () => {	
	var selectedCheckboxes = $('input[type="checkbox"]:checked');
	
    if(selectedCheckboxes.length == 0) {
		$('#formMsg').text('No data to make inactive!');
	}
	else {
		$('#formMsg').text('');
		$('#comfirmModal').modal('show');
		$('#modalMsg').text('Are you sure to make inactive?');
		$('button[name="modalSubmit"]').val('makeInActive');
	}
});

$('#checkAll').on('change', () => {
	if ($('#checkAll').is(':checked')) {
		$('tbody input[type="checkbox"]').prop('checked', true);
	}
	else {
		$('tbody input[type="checkbox"]').prop('checked', false);
	}
});

checkboxes.each(function(i, e) {
	$(e).on('change', () => {
		if(!$(e).is(':checked')) {
			$('#checkAll').prop('checked', false);
		}
	})
})
