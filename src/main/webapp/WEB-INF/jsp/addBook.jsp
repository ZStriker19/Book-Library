<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class = "container">

<h1>Enter New Book Information:</h1>
<c:url var="formAction" value="/addBook/submit"/>

<form id="addBook" method="POST" action="${formAction}">

	<div class="form-group row">
  		<label for="new-book-title" class="col-2 col-form-label">Title</label>
  		<div class="col-10">
    		<input class="form-control" type="text" placeholder="Title" id="title-input" name="title-input">
  		</div>
  	</div>
  
    <div class="form-group row">
  		<label for="new-book-location" class="col-2 col-form-label">Section</label>
  		<div class="col-10">
    		<input class="form-control" type="text" placeholder="Section in Library" id="section-input" name="section-input">
    	</div>
  	</div>
  
  	<div class="form-group row">
  		<div class = "col-sm-6">
  			<label for="new-book-first-name" class="col-2 col-form-label">Author's First Name</label>
    		<input class="form-control" type="text" placeholder="First name" id="first-name-input" name="first-name-input">
    	</div>
    	<div class = "col-sm-6">
  			<label for="new-book-last-name" class="col-2 col-form-label">Author's Last Name</label>
    		<input class="form-control" type="text" placeholder="Last name" id="last-name-input" name="last-name-input">
    	</div>
  	</div>
  
  	<div class="form-group row">
  		<div class = "col-sm-6">
  			<label for="new-book-character-1-first-name" class="col-2 col-form-label">Character 1 First Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 1 First Name" id="character-1-first-name-input" name="character-1-first-name-input">
    		</div>
    	</div>
    	<div class = "col-sm-6">
    		<label for="new-book-character-1-last-name" class="col-2 col-form-label">Character 1 Last Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 1 Last Name" id="character-1-last-name-input" name="character-1-last-name-input">
    		</div>
		</div>
  	</div>
  
  	<div class="form-group row">
  		<label for="new-book-genres" class="col-2 col-form-label">Genres</label>
  		<div class="col-10">
    		<input class="form-control" type="text" placeholder="genres" id="genres-input" name="genres-input">
    	</div>
    	<input class="formSubmitButton" type="submit" value="Add Book" />
  	</div>
  </form>
</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>