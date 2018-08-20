<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>


<div class="container t-3">

<h1>Books to Read</h1>
<form action="addToBooksYouHaveRead">
	<table class="table">
	  <tr>
	    <th>Book Title</th>
	    <th>Author</th>
	    
	    <th>

	  <div class="dropdown">
  <button onclick="booksRead()" class="dropbtn">Options</button>
  <div id="booksRead" class="dropdown-content">
  <input type="submit" value="Add To Books You Have Read">
  
  <input type="submit" value="Delete Books You Have Read" formaction="/deleteFromBooksYouHaveRead">

	    
 <div class="dropdown">
	    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Options
	    <span class="caret"></span></button>
	    <ul class="dropdown-menu">
	      <li><input class="dropdown-item formSubmitButton btn btn-primary" type="submit" value="addToBooksYouHaveRead"/> </li>
	      <li><input class="dropdown-item formSubmitButton btn btn-primary" type="submit" value="Delete Books You Will Read" formaction="deleteFromBooksToRead"/></li>
	    </ul>

  </div>
	    
	    
<script>

function booksRead() {
    document.getElementById("booksRead").classList.toggle("show");
}

window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>
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
		<td> <input type="checkbox" name="checkbox" value="${book.bookId}">
		</td>
	  </tr>
	  </c:forEach>
	</table>
	</form>


<h1>Books You've Read</h1>
<form action="addBooksToRead">
<table class="table">
	  <tr>
	    <th>Book Title</th>
	    <th>Author</th>
	    <th>
	    
	    <div class="dropdown">
	    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Options
	    <span class="caret"></span></button>
	    <ul class="dropdown-menu">
	      <li><input class="formSubmitButton btn btn-primary" type="submit" value="addBooksToRead"> </li>
	      <li><input class="formSubmitButton btn btn-primary" type="submit" value="Delete Books You Have Read" formaction="deleteFromBooksYouHaveRead"></li>
	    </ul>
  </div>
	    
	    
	<!--   <div class="dropdown">
  <button onclick="readBooks()" class="dropbtn">Options</button>
  <div id="readBooks" class="dropdown-content">
<<<<<<< HEAD
    <input type="submit" value="Add To Reading List">
=======
    <input class="formSubmitButton btn btn-primary" type="submit" value="addBooksToRead">
>>>>>>> 43caaec2dcf801c0e463f15d33ada8bddf167b5c
  
  	<input class="formSubmitButton btn btn-primary" type="submit" value="Delete Books You Have Read" formaction="deleteFromBooksYouHaveRead">
  
  </div>
</div> -->
<script>

function readBooks() {
    document.getElementById("readBooks").classList.toggle("show");
}

window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {

    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>
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
		<td> <input type="checkbox" name="checkbox" value="${book.bookId}">
		</td>
	  </tr>
	  </c:forEach>
	</table>
</form>
</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>