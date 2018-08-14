<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<h1>Please search for a book by: Title, Author, Character, Keyword, or Location </h1>
<form class="form-inline">
<div>
	<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
    <i class="fa fa-search" aria-hidden="true"></i>
    <input class="form-control form-control-sm ml-3 w-75" type="text" placeholder="Search" aria-label="Search">
    
  
    </div>
</form>

<c:import url="/WEB-INF/jsp/footer.jsp"/>