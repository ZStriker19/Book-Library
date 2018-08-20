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
    <label class="form-check-label" for="newSearch">Search only books added since last search</label> 
  </div>
  
    <input class="formSubmitButton btn btn-primary" type="submit" value="Search" />
  
    </div>

</form>
</div>

<div class="container t-3">
	<table class="table">
	
  <tr>
    <th>Book Title</th>
    <th>Author</th>
    <th>Add to Reading List<th>
    <th><div class="btn-group">
  <button type="button" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Reading List Options
  </button>
  <div class="dropdown-menu">
    <a class="dropdown-item" href="#">Add to Books to Read</a>
    <a class="dropdown-item" href="#">Add to Books I Have Read</a>
  </div>
</div>
</th>
    
  </tr>
  <c:forEach items="${books}" var="book">
  <tr>
   <td>
   <c:out value="${book.title}"/>
   </td>
   <td>
     <c:out value="${book.getAuthorFullNames()}"></c:out>
	</td> 
	<td>
		<div class="form-check">
    	<input type="checkbox" class="form-check-input" id="addToReadingListCheck">
    	<label class="form-check-label" for="addToReadingList"></label>
  		</div>
    </td>
    
  </tr>
  </c:forEach>
</table>

<c:if test="${messageNoBooks != null}">
	<div class="alert alert-danger alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
	${messageNoBooks}
	</div>
	</c:if>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>
