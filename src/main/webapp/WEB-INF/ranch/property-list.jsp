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
<title>Ranch Listing</title>

</head>

<body>
	<div class="container">
		<h1>
			Visibility:  ADMN, GUEST
		</h1>
		<h2>
		
		a table of all horse property/ranches for guest user for potential subscriptions
-- each row has a link so GUESTs can see details on the next page
		
		</h2>
	
		<div class="row">
			<div class="col-25">
				<a href="/ranches/owners-properties">See your owners page...if you are an owner</a>
			</div>
			<div class="col-25">
				<a href="/ranches/property-details">Property Details</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
	
	</div>

</body>
</html>