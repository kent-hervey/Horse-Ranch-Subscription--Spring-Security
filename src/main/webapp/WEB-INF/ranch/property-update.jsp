<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/styles2.css" />
<meta charset="ISO-8859-1">
<title>Update Ranch</title>
</head>
<body>
	<div class="container">

		<h1>Welcome Ranch Owner ${ranchOnwerFullName}</h1>
		<h2>Below Edit "${horseRanch.ranchName }" with id of
			${horseRanch.id }</h2>

		<div class="row"> 
			<div class="col-50">
				<form:form action="/ranches/${horseRanch.id}" method="POST"
					modelAttribute="horseRanch">
					<input type="hidden" name="_method" value="put">

					<form:label path="ranchName">Name (Name/short description 5-30 characters)</form:label>
					<form:errors class="bad"  path="ranchName" />
					<form:input path="ranchName" />

					<form:label path="numberAcres">Acres (Minimum 2, Maximum 100,000)</form:label>
					<form:errors class="bad"  path="numberAcres" />
					<form:input path="numberAcres" />

					<form:label path="horseCapacity">Horse Capacity (Minimum 2, Maximum 1,000)</form:label>
					<form:errors class="bad"  path="horseCapacity" />
					<form:input path="horseCapacity" />

					<form:label path="peopleCapacity">People Capacity (Minimum 2, Maximum 100)</form:label>
					<form:errors class="bad"  path="peopleCapacity" />
					<form:input path="peopleCapacity" />

					<form:label path="location">Location</form:label>
					<form:errors class="bad"  path="location" />
					<form:input path="location" />

					<form:label path="annualSubscriptionPrice">Annual Subscription Price (Minimum $100, Maximum $100,000)</form:label>
					<form:errors class="bad"  path="annualSubscriptionPrice" />
					<form:input path="annualSubscriptionPrice" />

					<form:label path="ranchDescription">Description (up to 250 characters)</form:label>
					<form:errors class="bad"  path="ranchDescription" />
					<form:input path="ranchDescription" />

					<input type="submit" value="Update">

				</form:form>
			</div>

		</div>
		<!-- end row -->
		<div class="row">
			<div class="col-25">
				<a href="/browsers/users/auth">My Personal Page</a>
			</div>
			<div class="col-25">
				<a href="/owners/auth">My Ranches</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
	</div>
	<!-- end container div -->


</body>
</html>