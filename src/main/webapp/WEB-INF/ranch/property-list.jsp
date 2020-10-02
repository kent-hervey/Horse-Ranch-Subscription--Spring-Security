<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/css/styles2.css"/>
<meta charset="ISO-8859-1">
<title>Ranch Listing for Guests</title>
<!-- a table of all horse ranches for guest user for potential subscriptions
-- each row has a link so GUESTs can see details on the next page -->

</head>

<body>
	<div class="container">
		<h4>
			Visibility:  ADMN, GUEST
		</h4>		
		<div class="row">
			<form id="logoDFEDFutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input class="right-link normal-link"
					type="submit" value="logout!" />
			</form>
		</div>
		

		
		<h1>Welcome, ${loggedInUser.firstName} ${loggedInUser.lastName } id:  ${loggedInUser.id } </h1>
		
		
				<table class="tablestyle">
			<caption>
				<h3>
					All Ranches
				</h3>
			</caption>
			<thead>
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Horse Capacity</th>
					<th>Location</th>
					<th>Annual Subscription Price</th>
					<th># Current Subscribers</th>
					<th>Am I Subscribed?</th>
					<th>Owner's Id</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${horseRanches}" var="horseRanch" varStatus="loopCounter">
					<tr>
						<td>${loopCounter.count }</td>
						<td><a href="/ranches/property-details-guest/${horseRanch.id}">${horseRanch.ranchName}</a>--id:  ${horseRanch.id}</td>
						<td>${horseRanch.horseCapacity}</td>
						<td>${horseRanch.location}</td>
						<td>${horseRanch.annualSubscriptionPrice}</td>	
						<td>${horseRanch.getSubscriberSize() }</td>	
						<td>
							<c:choose>
								<c:when test = "${horseRanch.isUserSubscribingThisRanch(loggedInUser) }">
									Yes
								</c:when>
								<c:otherwise>
									No
								</c:otherwise>
							</c:choose>
						</td>
						<td>${horseRanch.ranchOwner.getId() }</td>
					</tr>
				</c:forEach>
			
			
			
			
			
			</tbody>
		</table>
		

		
		
		</h4>
	
		<div class="row">

			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>

			<div class="col-25">
				<a href="/user-details">My Personal Page</a>
			</div>
		</div>
	
	</div>

</body>
</html>