<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>


<div class="forumPosts">
<h1>Foxtrot Library Forum</h1><br />
<c:url var="newPostURL" value="/forum/newPost"/>
<a class="link" href="${newPostURL}">Post a message</a>
<c:forEach var="post" items="${posts}">

<div class="row">
<h2>${post.subject}</h2>
<p>by ${post.username} on ${post.getDatePostedStr()}</p>
<p>${post.message}</p>
</div>

</c:forEach>


</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>