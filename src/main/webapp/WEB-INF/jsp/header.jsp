<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

	<head>
		<title>Foxtrot Public Library</title>
		<c:url var="cssHref" value="/css/site.css" />
		<link rel="stylesheet" type="text/css" href="${cssHref}">
		<!-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> -->
		<c:url var="bootstrapcss" value="/css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="${bootstrapcss}">
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	    <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/jquery.validate.min.js"></script>
	    <script src="http://cdn.jsdelivr.net/jquery.validation/1.15.0/additional-methods.js "></script>
	    <script src="https://cdn.jsdelivr.net/jquery.timeago/1.4.1/jquery.timeago.min.js"></script>
	   <!--  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
    	
    	<c:url var="bootstrapjs" value="/js/bootstrap.min.js"/>
		<script src="${bootstrapjs}"></script> 
		
		<c:url var="cssHref" value="/css/validation.css" />
		<link rel="stylesheet" type="text/css" href="${cssHref}">
		
		 <script> 
		 $(document).ready(function() {
				$("time.timeago").timeago();
							
				$("#logoutLink").click(function(event){
				$("#logoutForm").submit();
									});
							
				var pathname = window.location.pathname;
				$("nav a[href='"+pathname+"']").parent().addClass("active");
							
			});
				
		 </script>
		
		<c:url var="formVal" value="/js/formValidation.js"/> 
		<script src="${formVal}"></script> 
		
	</head>
	<body>
	
	<div class="headerDiv">
		<header>
	
			<c:url var="homePageHref" value="/" />
			<c:url var="imgSrc" value="/img/Foxtrotlogofinal1.png" />
			<%-- <img src="${homePageHref}img/Foxtrotlogofinal1.png"> --%>
			<a href="${homePageHref}"><img src="${imgSrc}" class="img-responsive" /></a>
		</header>
		</div>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
			<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navCollapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			</div>
			
			<div class="collapse navbar-collapse" id="navCollapse">
				<ul class="nav navbar-nav">
				
			
				
					<c:url var="homePageHref" value="/" />
					<li><a href="${homePageHref}">Home</a></li>
					
					
					<c:if test="${currentUser.role == true }">
						<c:url var="addBookHref" value="/addBook" />
						<li><a href="${addBookHref}">Add Book</a></li>
					</c:if>
					
					
					<c:if test="${not empty currentUser}">
						<c:url var="readingLists" value="/readingLists" />
						<li><a href="${readingLists}">Reading Lists</a></li>
						
						<c:url var="dashboardHref" value="/users/${currentUser}" />
						<c:url var="forumLink" value="/users/forum"/>
            			<li><a href="${forumLink}">Foxtrot Forum</a></li>    
					</c:if>
				</ul>
				<ul class="nav nav-bar navbar-nav navbar-right">
					<c:choose>
						<c:when test="${empty currentUser}">
							<c:url var="newUserHref" value="/users/new" />
							<li><a href="${newUserHref}">Sign Up</a></li>
							<c:url var="loginHref" value="/login" />
							<li><a href="${loginHref}">Log In</a></li>
						</c:when>
						<c:otherwise>
							<c:url var="logoutAction" value="/logout" />
							<form id="logoutForm" action="${logoutAction}" method="POST">
							<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN}"/>
							</form>
							<li><a id="logoutLink" href="${logoutAction}">Log Out</a></li>
						</c:otherwise>
					</c:choose>
					
				</ul>
				</div>
			</div>
		</nav>
		<%-- <c:if test="${not empty currentUser}">
			<p id="currentUser">Current User: ${currentUser}</p>
		</c:if>		 --%>
		<div class="container">