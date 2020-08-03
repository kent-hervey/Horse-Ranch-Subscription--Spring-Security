<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/css/styles.css"/>
<meta charset="ISO-8859-1">
<title>An Owner's Property Information</title>

</head>

<body>
	<div class="container">
		<h1>
			Visibility:  OWNER, ADMN
		</h1>
		<h2>	
			Page contents will be specific to logged in user providing his non-personal information such as property listing
			
			contents include:
			--List of all this owner's properties in table
			----each row in table will have a delete button and edit button.  Edit button takes user to the Edit Property page
			
			Button or link at bottom to take user to the Add Property Page
			
			
		</h2>
	
	
	
	
	</div>
		<div class="row">
			<a href="/ranches/owners-add-property"><button>Create Horse Ranch</button></a>
		
		</div>
	
	
		<div class="row">
			<div class="col-25">
				<a href="/ranches/owners-properties">See your owners page...if you are an owner</a>
			</div>
			<div class="col-25">
				<a href="/user-details">Your Personal Page</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>

</body>
</html>