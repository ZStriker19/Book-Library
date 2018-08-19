<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>


<div class="container t-3">

<h1>Books to Read</h1>
	<table class="table">
	  <tr>
	    <th>Book Title</th>
	    <th>Author</th>
	  </tr>
	  <c:forEach items="${booksToRead}" var="book">
	  <tr>
	   <td>
	   <c:out value="${book.title}"/>
	   </td>
	   <td>
	     <c:out value="${book.getAuthorFullNames()}"></c:out>
		 </td> 
		
	  </tr>
	  </c:forEach>
	</table>


<h1>Books You've Read</h1>
<table class="table">
	  <tr>
	    <th>Book Title</th>
	    <th>Author</th>
	  </tr>
	  <c:forEach items="${booksHaveRead}" var="book">
	  <tr>
	   <td>
	   <c:out value="${book.title}"/>
	   </td>
	   <td>
	     <c:out value="${book.getAuthorFullNames()}"></c:out>
		 </td> 
		
	  </tr>
	  </c:forEach>
	</table>

</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>