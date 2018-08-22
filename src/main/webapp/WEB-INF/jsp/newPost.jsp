<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>
<c:url var="cssHref" value="/css/forum.css" />
		<link rel="stylesheet" type="text/css" href="${cssHref}">

<div class="forum">

<c:url var="forumFormURL" value="/forum/newPost"/>

<h2>New Forum Post</h2>
<form method="POST" class="forms" action="${forumFormURL}">
	<div>
	<label for="username">Username:</label>
	<c:out value="${currentUser.userName}"/>
</div>
<div>
	<label for="subject">Subject:</label>
	<input type="text" id="subject" name="subject"/>
	</div>
	
	<div>
	<div>
	<label for="message">Message: </label></div>
	<textarea  id="message" name="message"></textarea>
	</div>
	
	<div>
	 <input class="formSubmitButton btn btn-primary" type="submit" value="Post" />
</div>
</form>	

</div>


<c:import url="/WEB-INF/jsp/footer.jsp"/>