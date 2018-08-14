<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<h1>Please search for a book by: Title, Author, Character, Keyword, or Location </h1>
<c:url var="formAction" value="/"/>
<form class="form-inline" method="POST" action="${formAction}">
<div>
	<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
    <i class="fa fa-search" aria-hidden="true"></i>
    <input id="queryString" name="queryString" class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Search" aria-label="Search">
    <input class="formSubmitButton" type="submit" value="Search" />
  
    </div>
</form>

	<table class="table">
	
  <tr>
    <th>Book Title</th>
    <th>Author</th>
  </tr>
  <c:forEach items="${books}" var="book">
  <tr>

    <td>${book.title}</td>
   <td>

   <td>
   <c:out value="${book.title}"/>
   </td>
   <td>

     <c:out value="${book.authorFirstNames}"></c:out>
	
	 <c:out value="${book.authorLastNames}"></c:out>
	 </td> 
  </tr>
  </c:forEach>
</table>

<c:import url="/WEB-INF/jsp/footer.jsp"/>
