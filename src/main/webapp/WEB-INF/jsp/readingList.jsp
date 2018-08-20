<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>


<div class="container t-3">

<h1>Books to Read</h1>
	<table class="table">
	  <tr>
	    <th>Book Title</th>
	    <th>Author</th>
	    
	    <th>
	  <div class="dropdown">
  <button onclick="booksRead()" class="dropbtn">Options</button>
  <div id="booksRead" class="dropdown-content">
    <a class="booksread" href="#">Add to Books You've Read</a>
    <a class="booksread" href="#">Delete From Reading List</a>
  </div>
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
		
	  </tr>
	  </c:forEach>
	</table>


<h1>Books You've Read</h1>
<table class="table">
	  <tr>
	    <th>Book Title</th>
	    <th>Author</th>
	    <th>
	  <div class="dropdown">
  <button onclick="readBooks()" class="dropbtn">Options</button>
  <div id="readBooks" class="dropdown-content">
    <a class="booksread" href="#">Add to Books You've Read</a>
    <a class="booksread" href="#">Delete From Reading List</a>
  </div>
</div>
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
		
	  </tr>
	  </c:forEach>
	</table>

</div>
<c:import url="/WEB-INF/jsp/footer.jsp"/>