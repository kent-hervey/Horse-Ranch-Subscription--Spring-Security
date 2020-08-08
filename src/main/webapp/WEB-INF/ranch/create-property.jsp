<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>



    
    
    

<html>
<head>
	<link rel="stylesheet" href="/css/styles2.css"/>
<meta charset="ISO-8859-1">
<title>Create Horse Ranch </title>
</head>
<body>

	<div class="container">
	<h1>Welcome, ${loggedInUser.firstName} ${loggedInUser.lastName }  xx</h1>
		<h1>New Horse Ranch</h1>
		<div class="row">
			<div class="col-50">
				<form:form action="/ranches/owners-add-property" method="POST" modelAttribute="horseRanch">
				
				<form:label path="ranchName">Name (short description <30 char)</form:label>
				<form:errors path="ranchName"/>
				<form:input path="ranchName"/>
				
				<form:label path="numberAcres">Acres (round, please)</form:label>
				<form:errors path="numberAcres"/>
				<form:input path="numberAcres"/>
				
				<form:label path="horseCapacity">Horse Capacity</form:label>
				<form:errors path="horseCapacity"/>
				<form:input path="horseCapacity"/>
				
				<form:label path="peopleCapacity">People Capacity</form:label>
				<form:errors path="peopleCapacity"/>
				<form:input path="peopleCapacity"/>
				
				<form:label path="location">Location</form:label>
				<form:errors path="location"/>
				<form:input path="location"/>
				
				<form:label path="annualSubscriptionPrice">Annual Subscription Price</form:label>
				<form:errors path="annualSubscriptionPrice"/>
				<form:input path="annualSubscriptionPrice"/>				
				
				<form:label path="ranchDescription">Description (up to 250 char)</form:label>
				<form:errors path="ranchDescription"/>
				<form:input path="ranchDescription"/>	
				
				<input type="submit" value="Create">
				
				</form:form>
			</div>

		</div><!-- end row -->
			
		<div class="row">
			<div class="col-25">
				<a href="/user-details">Home</a>
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








