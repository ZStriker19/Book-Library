/// <reference path="../jquery-3.1.1.js" />
/// <reference path="../jquery.validate.js" />


$(document).ready(function () {

	$("#search").validate( {

		debug: false,
		rules: {

			queryString: {
				required: true,
				
				minlength: 2
			}

		},
			messages: {
				queryString: {
					required: "You must provide a valid search"
				}
			},

			errorClass: "error",
			validClass: "valid"
	});
});