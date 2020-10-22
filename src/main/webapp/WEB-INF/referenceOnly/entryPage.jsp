<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/styles2.css"/>
<title>Horse Ranch Entry </title>
<!--This page can be viewed by anybody and will link to loginreg, and ONlY if logged in as Browser, will go to User page-->
</head>
<body>
	<div class="container">
    
    
    
		<div class="row">
			<div class="col-25">
				<a href="/ranches/owners-properties">See your owners page...if you are an owner</a>
			</div>
			<div class="col-25">
				<a href="/ranches/property-list">Property Listings</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
			<div class="col-25">
				<a href ="/ranches/property-details-guest">See property details for guests...needs id added</q>
			</div>
			<div class="col-25">
				<a href ="/ranches/property-details-owner">Your property details..needs id added</q>
			</div>
			
			
		</div>

	</div><!--end container-->

</body>
</html> 