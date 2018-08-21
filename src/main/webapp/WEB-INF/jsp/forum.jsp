<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>
<c:url var="cssHref" value="/css/forum.css" />
		<link rel="stylesheet" type="text/css" href="${cssHref}">



<h1>Foxtrot Library Forum</h1>

<c:url var="newPostURL" value="/forum/newPost"/>
<h3><a class="link" href="${newPostURL}">Post a message</a></h3>

<table class="formPosts">
<c:forEach var="post" items="${posts}">
<tr>
<th>
<h2>${post.subject}</h2>
</th>
</tr>
<tr>
<td>
<p><strong>By:</strong> ${post.username} on ${post.getDatePostedStr()}</p>
<p>${post.message}</p>
</td>
</tr>

</c:forEach>

</table>


<c:import url="/WEB-INF/jsp/footer.jsp"/>