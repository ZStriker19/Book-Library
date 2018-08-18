<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>


<div class="forum">

<c:url var="forumFormURL" value="/forum/newPost"/>

<h2>New Forum Post</h2>
<form method="POST" class="forms" action="${forumFormURL}">
	
	<label for="username">Username:</label>
	<input type="text" id="username" name="username" /><br />

	<label for="subject">Subject:</label>
	<input type="text" id="subject" name="subject" /><br />
	
	<label for="message">Message: </label>
	<input type="text" id="message" name="message" /><br />
	
	<input type="submit" value="submit" />

</form>	

</div>


<c:import url="/WEB-INF/jsp/footer.jsp"/>