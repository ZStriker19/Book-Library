
	
		$("#userLogin").validate({
			
			rules : {
				userName : {
					required : true, 
				},
				password : {
					required : true,
				}
			},
			messages : {		
				userName: {
					required: "Username is required",
				},
				password: {
					required: "Password is required",
				},
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});