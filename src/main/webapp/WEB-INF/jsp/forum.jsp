<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>
<c:url var="cssHref" value="/css/forum.css" />
		<link rel="stylesheet" type="text/css" href="${cssHref}">

<c:url var="newPostURL" value="/forum/newPost"/>
<link rel="buttonLink" href="${newPostURL}">
<h1>Foxtrot Library Forum</h1>

<a href="${newPostURL}"><button class="button">Post A Message</button></a>
<div class="formPosts">

<c:forEach var="post" items="${posts}">
<div class="onePost">
<h2>${post.subject}</h2>

<p><strong>By:</strong> ${post.username} on ${post.getDatePostedStr()}</p>
<p>${post.message}</p>

</div>
</c:forEach>

</div>


<c:import url="/WEB-INF/jsp/footer.jsp"/>