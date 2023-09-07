$('button').on('click', function(e) {
	
	if($('input[name="userName"]').val() === "") {	
		$('#unameMsg').text('Username must be not empty!');
		$('form').css('borderColor', 'red');
		e.preventDefault();	
	}
	else {
		$('#unameMsg').text('');
	}
	
	if($('input[name="password"]').val() === "") {		
		$('#passMsg').text('Password must be not empty!');
		$('form').css('borderColor', 'red');
		e.preventDefault();
	}
	else {
		$('#passMsg').text('');
	}
})