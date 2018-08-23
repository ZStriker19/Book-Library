$(document).ready(function(){
	
$("#addBook").validate({
				rules : {
					title : {
						required : true, 
				},
				section : {
					required : true,
					maxlength : 3
				},
				author1FirstName : {
					required : true,
				},
				author1LastName : {
					required : true,
				}
			},
			messages : {		
				title : {
					required: "Book title is required",	
				},
				section : {
					required: "Library section is required",
					maxlength: "Section can only contain up to 3 characters (e.g. A1, A2, A10, etc.)",	
				},
				author1FirstName : {
					required: "Author's first name is required",
				},
				author1LastName : {
					required: "Author's last name is required",
				}
			},
			errorClass : "error"
		});
});