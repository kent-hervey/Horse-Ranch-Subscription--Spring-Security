<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/styles.css"/>
<title>Dashboard</title>
</head>
<body>
	<div class="container">
	
		<div class="row">
			<form id="logoDFEDFutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input class="right-link normal-link" type="submit" value="logout!" />
			</form>
		</div>

	<div class="row">
		<h1>Welcome Plain Old User ${currentUser.firstName}</h1>
	</div>
	
		<div class= "divBorder">
			<div class="row">
				<div class="col-50">
					<h2>First Name:</h2>
				</div>
				<div class="col-50">
					<h2>${currentUser.firstName}</h2>
				</div>		
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Last Name: </h2>
				</div>
				<div class="col-50">
					<h2>${currentUser.lastName}</h2>
				</div>		
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Email: </h2>
				</div>
				<div class="col-50">
					<h2>${currentUser.email}</h2>
				</div>		
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Sign up date: </h2>
				</div>
				<div class="col-50">
					<h2><fmt:formatDate pattern="MMM dd, yyyy" value="${currentUser.createdAt}" /></h2>
				</div>		
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Last Sign in: </h2>
				</div>
				<div class="col-50">
					<h2>
					<fmt:formatDate pattern="MMM dd, yyyy" value="${currentUser.lastSignIn}" /><!-- was "MMM dd, yyyy hhh mmm ssss" -->
					</h2>
				</div>		
			</div>
			
			
			
	
		</div><!-- end divBorder -->

	</div><!--end container-->

</body>
</html> 