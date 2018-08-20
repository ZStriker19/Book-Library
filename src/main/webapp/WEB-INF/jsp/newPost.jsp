<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>


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
	<input type="text" id="subject" name="subject" /><br />
	</div>
	<div>
	<label for="message">Message: </label>
	<input type="text" id="message" name="message" /><br />
	</div>
	<div>
	<input type="submit" value="submit" />
</div>
</form>	

</div>


<c:import url="/WEB-INF/jsp/footer.jsp"/>