<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>


<div class="container t-3">

<h1>Want to Read</h1>
<form action="addToBooksYouHaveRead">
	<table class="table" >
	  <tr>
	    <th>Book Title</th>
	    <th>Author</th>
	    
	    <th>

	    
 <div class="dropdown">
	    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Options
	    <span class="caret"></span></button>
	    <ul class="dropdown-menu">
	      <li><input class="dropdown-item formSubmitButton btn btn-link" style="text-decoration:none" type="submit" value="Have Read"/> </li>
	      <li><input class="dropdown-item formSubmitButton btn btn-link" style="text-decoration:none" type="submit" value="Delete From List" formaction="deleteFromBooksToRead"/></li>
	    </ul>

  </div>
	    
	    

</th>
	  </tr>
	  <c:forEach items="${booksToRead}" var="book">
	  <tr>
	   <td>
	   <c:out value="${book.title}"/>
	   </td>
	   <td>
	     <c:out value="${book.getAuthorFullNames()}"></c:out>
		 </td> 
		<td> <input type="checkbox" name="checkbox${book.bookId}" value="${book.bookId}">
		</td>
	  </tr>
	  </c:forEach>
	</table>
	</form>


<h1>Have Read</h1>
<form action="addBooksToRead">
<table class="table">
	  <tr>
	    <th>Book Title</th>
	    <th>Author</th>
	    <th>
  
	    <div class="dropdown show">
	    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Options
	    <span class="caret"></span></button>
	    <ul class="dropdown-menu">
	      <li><input class="formSubmitButton btn btn-link" type="submit" style="text-decoration:none" value="Want to Read"> </li>
	      <li><input class="formSubmitButton btn btn-link" type="submit" style="text-decoration:none" value="Delete From List" formaction="deleteFromBooksYouHaveRead"></li>
	    </ul>
  </div>


</th>
	  </tr>
	  <c:forEach items="${booksHaveRead}" var="book">
	  <tr>
	   <td>
	   <c:out value="${book.title}"/>
	   </td>
	   <td>
	     <c:out value="${book.getAuthorFullNames()}"></c:out>
		 </td> 
		<td> <input type="checkbox" name="checkbox${book.bookId}" value="${book.bookId}">
		</td>
	  </tr>
	  </c:forEach>
	</table>
</form>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>