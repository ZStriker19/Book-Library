<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class = "container">
<c:if test="${message != null}">
	<div class="alert alert-success alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
	${message}
	</div>
	</c:if>
<h1>Search by:  </h1>

<c:url var="formAction" value="/search" />

<form id="search" name="search" method="GET" action="${formAction}">
<div>
    <i class="fa fa-search" aria-hidden="true"></i>
    <input name="queryString"  id="queryString" class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Title, Author, Character, Genre (e.g. fiction, non-fiction, children etc.), or Location In Library" aria-label="Search"/>

    <div class="form-check">
   		<input type="checkbox" class="form-check-input" id="newSearch" name="newSearch" value="checked">
    <label class="form-check-label" for="newSearch">Search Only New Books</label> 
  </div>
  
    <input class="formSubmitButton btn btn-primary" type="submit" value="Search" />
  
    </div>

</form>
</div>


<div class="container t-3">
<form action="addToWillReadList" method="POST" >
	<table class="table">
	
  <tr>
    <th>Book Title</th>
    <th>Author</th>
    <c:if test="${not empty currentUser}">
   <th>
     <div class="dropdown show">
   
	    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Options
	    <span class="caret"></span></button>
	    <ul class="dropdown-menu">
	      <li><input class="formSubmitButton btn btn-link" type="submit" style="text-decoration:none" value="Want to Read"> </li>
	      <li><input class="formSubmitButton btn btn-link" type="submit" style="text-decoration:none" value="Have Read" formaction="addToHaveReadList"></li>
	    </ul>
	   
  </div>
</th>
</c:if>
    
  </tr>
  <c:forEach items="${books}" var="book">
  <tr>
   <td>
   <c:out value="${book.title}"/>
   </td>
   <td>
     <c:out value="${book.getAuthorFullNames()}"></c:out>
	</td> 
	<c:if test="${not empty currentUser}">
		<td> <input type="checkbox" name="checkbox${book.bookId}" value="${book.bookId}">
		</td>
    </c:if>
  </tr>
  </c:forEach>
</table>
</form>

<c:if test="${messageNoBooks != null}">
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
	${messageNoBooks}
	</div>
	</c:if>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>
