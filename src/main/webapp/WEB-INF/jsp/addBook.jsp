<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="/WEB-INF/jsp/header.jsp"/>

<h1>Enter New Book Information:</h1>

<div class="form-group row">
  <label for="new-book-title" class="col-2 col-form-label">Title</label>
  <div class="col-10">
    <input class="form-control" type="text" placeholder="Title" id="title-input">
  </div>
  
    <div class="form-group row">
  <label for="new-book-location-city" class="col-2 col-form-label">Location: City </label>
  <div class="col-10">
    <input class="form-control" type="text" placeholder="Publication City" id="location-city-input">
  </div>
  
  <div class="form-group row">
  	<label for="new-book-location-country" class="col-2 col-form-label">Location: Country</label>
  	<div class="col-10">
    <input class="form-control" type="text" placeholder="Publication Country" id="location-country-input">
  </div>
  
  <div class="form-group row">
  <label for="new-book-first-name" class="col-2 col-form-label">Author's First Name</label>
  <div class="col-10">
    <input class="form-control" type="text" placeholder="First name" id="first-name-input">
  </div>
  
  <div class="form-group row">
  <label for="new-book-last-name" class="col-2 col-form-label">Author's Last Name</label>
  <div class="col-10">
    <input class="form-control" type="text" placeholder="Last name" id="last-name-input">
  </div>
  
  <div class="form-group row">
  <label for="new-book-character-1" class="col-2 col-form-label">Character 1</label>
  <div class="col-10">
    <input class="form-control" type="text" placeholder="Character 1" id="character-1-input">
  </div>
  
  <div class="form-group row">
  <label for="new-book-keyword" class="col-2 col-form-label">Keyword</label>
  <div class="col-10">
    <input class="form-control" type="text" placeholder="Keyword" id="keyword-input">
  </div>
  


<c:import url="/WEB-INF/jsp/footer.jsp"/>