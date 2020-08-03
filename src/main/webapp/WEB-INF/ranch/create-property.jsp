<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>



    
    
    

<html>
<head>
	<link rel="stylesheet" href="/css/styles.css"/>
<meta charset="ISO-8859-1">
<title>Create Horse Ranch </title>
</head>
<body>

	<div class="container">
		<h1>New Horse Ranch</h1>
		<div class="row">
			<div class="col-50">
				<form:form action="/ranches/owners-add-property" method="POST" modelAttribute="horseRanch">
				
				
				
				
				
				
				
				
				<input type="submit" value="Create">
				
				</form:form>
			
			
			
			
			
			
			
			
			</div>
		
		
		
		
		
		
		
		
		
		
		
		
		
		
------seed new product form on ProductsCustomers

		</div><!-- end row -->
			
		<div class="row">
			<div class="col-25">
				<a href="">Home</a>
			</div>
			<div class="col-25">
				<a href="/ranches/owners-properties">My properties</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
		
	</div><!-- end container -->


</body>
</html>








