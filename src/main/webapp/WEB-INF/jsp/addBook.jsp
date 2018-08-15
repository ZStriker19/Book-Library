<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<div class = "container">

<h1>Enter New Book Information:</h1>

	<div class="form-group row">
  		<label for="new-book-title" class="col-2 col-form-label">Title</label>
  		<div class="col-10">
    		<input class="form-control" type="text" placeholder="Title" id="title-input">
  		</div>
  	</div>
  
    <div class="form-group row">
  		<label for="new-book-location" class="col-2 col-form-label">Location </label>
  		<div class="col-10">
    		<input class="form-control" type="text" placeholder="Location in Library" id="location-input">
    	</div>
  	</div>
  
  	<div class="form-group row">
  		<div class = "col-sm-6">
  			<label for="new-book-first-name" class="col-2 col-form-label">Author's First Name</label>
    		<input class="form-control" type="text" placeholder="First name" id="first-name-input">
    	</div>
    	<div class = "col-sm-6">
  			<label for="new-book-last-name" class="col-2 col-form-label">Author's Last Name</label>
    		<input class="form-control" type="text" placeholder="Last name" id="last-name-input">
    	</div>
  	</div>
  
  	<div class="form-group row">
  		<div class = "col-sm-6">
  			<label for="new-book-character-1-first-name" class="col-2 col-form-label">Character 1 First Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 1 First Name" id="character-1-first-name- input">
    		</div>
    	</div>
    	<div class = "col-sm-6">
    		<label for="new-book-character-1-last-name" class="col-2 col-form-label">Character 1 Last Name</label>
  			<div class="col-10">
    			<input class="form-control" type="text" placeholder="Character 1 Last Name" id="character-1-last-name- input">
    		</div>
		</div>
  	</div>
  
  	<div class="form-group row">
  		<label for="new-book-keyword" class="col-2 col-form-label">Keyword</label>
  		<div class="col-10">
    		<input class="form-control" type="text" placeholder="Keyword" id="keyword-input">
    	</div>
  	</div>
  
</div>

<c:import url="/WEB-INF/jsp/footer.jsp"/>