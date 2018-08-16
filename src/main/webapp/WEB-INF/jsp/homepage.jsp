<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class = "container">
<h1>Please search for a book by: Title, Author, Character, Keyword, or Location </h1>

<h1>Search by:  </h1>
<c:url var="formAction" value="/search"/>

<form id="search"  class="form-inline" method="GET" action="${formAction}">
<div>
	<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
    <i class="fa fa-search" aria-hidden="true"></i>
    <input name="queryString" class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Title, Author, Character, Keyword, or Location" aria-label="Search"/>
    <input class="formSubmitButton" type="submit" value="Search" />
  
    </div>

</form>
</div>
<div class="container t-3">
	<table class="table">
	
  <tr>
    <th>Book Title</th>
    <th>Author</th>
  </tr>
  <c:forEach items="${book}" var="books">
  <tr>
   <td>
   <c:out value="${books.title}"/>
   </td>
   <td>

     <c:out value="${books.getAuthorFullNames()}"></c:out>
	
	 </td> 
	
  </tr>
  </c:forEach>
</table>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>
