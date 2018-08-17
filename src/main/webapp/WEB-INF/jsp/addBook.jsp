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
  
  	<div class="form-group row" id="author1">
  		<div class = "col-sm-6">
  			<label for="new-book-first-name" class="col-2 col-form-label">Author's First Name</label>
    		<input class="form-control" type="text" placeholder="Author 1 First Name" id="author-1-first-name" name="author-1-first-name">
    	</div>
    	<div class = "col-sm-6">
  			<label for="new-book-last-name" class="col-2 col-form-label">Author's Last Name</label>
    		<input class="form-control" type="text" placeholder="Author 1 Last Name" id="author-1-last-name" name="author-1-last-name">
    	</div>
  	</div>
  	
  	<div class="form-group row initiallyHidden" id = "author2">
  		<div class = "col-sm-6">
  			<label for="new-book-first-name" class="col-2 col-form-label">Author's First Name</label>
    		<input class="form-control" type="text" placeholder="Author 2 First Name" id="author-2-first-name" name="author-2-first-name">
    	</div>
    	<div class = "col-sm-6">
  			<label for="new-book-last-name" class="col-2 col-form-label">Author's Last Name</label>
    		<input class="form-control" type="text" placeholder="Author 2 Last Name" id="author-2-last-name" name="author-2-last-name">
    	</div>
  	</div>
  	
  	<div class="form-group row initiallyHidden" id = "author3">
  		<div class = "col-sm-6">
  			<label for="new-book-first-name" class="col-2 col-form-label">Author's First Name</label>
    		<input class="form-control" type="text" placeholder="Author 3 First Name" id="author-3-first-name" name="author-3-first-name">
    	</div>
    	<div class = "col-sm-6">
  			<label for="new-book-last-name" class="col-2 col-form-label">Author's Last Name</label>
    		<input class="form-control" type="text" placeholder="Author 3 Last Name" id="author-3-last-name" name="author-3-last-name">
    	</div>
  	</div>
  	
  	<div class="form-group row initiallyHidden" id = "author4">
  		<div class = "col-sm-6">
  			<label for="new-book-first-name" class="col-2 col-form-label">Author's First Name</label>
    		<input class="form-control" type="text" placeholder="Author 4 First Name" id="author-4-first-name" name="author-4-first-name">
    		
    	</div>
    	<div class = "col-sm-6">
  			<label for="new-book-last-name" class="col-2 col-form-label">Author's Last Name</label>
    		<input class="form-control" type="text" placeholder="Author 4 Last Name" id="author-4-last-name" name="author-4-last-name">
    		
    	</div>
  	</div>
  	
  	<input type="button" name="add-another-author" id="add-another-author" class="btn btn-success btn-file clone" value="Add Additional Author">
  
  	<div class="form-group row" id = "character1">
  		<div class = "col-sm-6">
  			<label for="new-book-character-1-first-name" class="col-2 col-form-label">Character First Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 1 First Name" id="character-1-first-name" name="character-1-first-name">
    		</div>
    	</div>
    	<div class = "col-sm-6">
    		<label for="new-book-character-1-last-name" class="col-2 col-form-label">Character Last Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 1 Last Name" id="character-1-last-name" name="character-1-last-name">
    		</div>
		</div>
  	</div>
  	
  	<div class="form-group row initiallyHidden" id = "character2">
  		<div class = "col-sm-6">
  			<label for="new-book-character-1-first-name" class="col-2 col-form-label">Character First Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 2 First Name" id="character-2-first-name" name="character-2-first-name">
    			
    		</div>
    	</div>
    	<div class = "col-sm-6">
    		<label for="new-book-character-1-last-name" class="col-2 col-form-label">Character Last Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 2 Last Name" id="character-2-last-name" name="character-2-last-name">
    		</div>
		</div>
  	</div>
  	
  	<div class="form-group row initiallyHidden" id = "character3">
  		<div class = "col-sm-6">
  			<label for="new-book-character-1-first-name" class="col-2 col-form-label">Character First Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 3 First Name" id="character-3-first-name" name="character-3-first-name">
    		</div>
    	</div>
    	<div class = "col-sm-6">
    		<label for="new-book-character-1-last-name" class="col-2 col-form-label">Character Last Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 3 Last Name" id="character-3-last-name" name="character-3-last-name">
    		</div>
		</div>
  	</div>
  	
  	<div class="form-group row initiallyHidden" id = "character4">
  		<div class = "col-sm-6">
  			<label for="new-book-character-1-first-name" class="col-2 col-form-label">Character First Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 4 First Name" id="character-4-first-name" name="character-4-first-name">
    		</div>
    	</div>
    	<div class = "col-sm-6">
    		<label for="new-book-character-1-last-name" class="col-2 col-form-label">Character Last Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 4 Last Name" id="character-4-last-name" name="character-4-last-name">
    		</div>
		</div>
  	</div>
  	
  	<div class="form-group row initiallyHidden" id = "character5">
  		<div class = "col-sm-6">
  			<label for="new-book-character-1-first-name" class="col-2 col-form-label">Character First Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 5 First Name" id="character-5-first-name" name="character-5-first-name">
    		</div>
    	</div>
    	<div class = "col-sm-6">
    		<label for="new-book-character-1-last-name" class="col-2 col-form-label">Character Last Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 5 Last Name" id="character-5-last-name" name="character-5-last-name">
    		</div>
		</div>
  	</div>
  	
  	<div class="form-group row initiallyHidden"  id = "character6">
  		<div class = "col-sm-6">
  			<label for="new-book-character-1-first-name" class="col-2 col-form-label">Character First Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 6 First Name" id="character-6-first-name" name="character-6-first-name">
    			
    		</div>
    	</div>
    	<div class = "col-sm-6">
    		<label for="new-book-character-1-last-name" class="col-2 col-form-label">Character Last Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 6 Last Name" id="character-6-last-name" name="character-6-last-name">
    			
    		</div>
		</div>
  	</div>
  	
  	<input type="button" name="add-another-character" id="add-another-character" class="btn btn-success btn-file clone" value="Add Additional Character">
  	
  	<div class="form-group row" id = "genre1">
  		<label for="new-book-genres" class="col-2 col-form-label">Genre</label>
  		<div class="col-10">
    		<input class="form-control" type="text" placeholder="Genre 1" id="genre-1" name="genre-1">
    	</div>
    	
  	</div>
  	
  	<div class="form-group row initiallyHidden" id = "genre2">
  		<label for="new-book-genres" class="col-2 col-form-label">Genre</label>
  		<div class="col-10">
    		<input class="form-control" type="text" placeholder="Genre 2" id="genre-2" name="genre-2">
    	</div>
    	
  	</div>
  	
  	<div class="form-group row initiallyHidden" id = "genre3">
  		<label for="new-book-genres" class="col-2 col-form-label">Genre</label>
  		<div class="col-10">
    		<input class="form-control" type="text" placeholder="Genre 3" id="genre-3" name="genre-3">
    	</div>
  	</div>
  	<input type="button" name="add-another-genre" id="add-another-genre" class="btn btn-success btn-file clone" value="Add Additional Genre">
    	
    <input class="formSubmitButton btn-success" type="submit" value="Add Book" />
  </form>
</div>

<c:url var="formVal" value="/js/addBook.js"/> 
<script src="${formVal}"></script> 
<c:import url="/WEB-INF/jsp/footer.jsp"/>