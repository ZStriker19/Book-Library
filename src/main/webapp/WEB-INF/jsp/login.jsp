<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<script type="text/javascript">
	$(document).ready(function () {
	
		$("#userLogin").validate({
			
			rules : {
				userName : {
					required : true,
					minlength : 3 
				},
				password : {
					required : true,
					minlength : 8
				}
			},
			messages : {		
				userName: {
					required: "Username is required!",
					minlength: "Username must be at least 3 characters"
				}
			},
				confirmPassword : {
					equalTo : "Passwords do not match"
				}
			},
			errorClass : "error"
		});
	});
</script>

<!-- <script>
jQuery.extend(jQuery.validator.messages, {
    required: "Username and password are required.",
    minlength: jQuery.validator.format("Username must be at least 3 characters."),
});

</script> -->
	

<div class="row">
	<div class="col-sm-4"></div>
	<div class="col-sm-4">
		<c:url var="formAction" value="/login" />
		<form id="userLogin" name="userLogin" method="POST" action="${formAction}">
		<input type="hidden" name="destination" value="${param.destination}"/>
		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
			<div class="form-group">
				<label for="userName">User Name: </label>
				<input type="text" id="userName" name="userName" placeHolder="User Name" class="form-control" />
			</div>
			<div class="form-group">
				<label for="password">Password: </label>
				<input type="password" id="password" name="password" placeHolder="Password" class="form-control" />
			</div>
			<button type="submit" class="btn btn-default">Login</button>
		</form>
	</div>
	<div class="col-sm-4"></div>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp" />