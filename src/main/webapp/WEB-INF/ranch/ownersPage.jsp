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
<title>An Owner's Ranch Information</title>
<!--includes table of this owner's ranches-->
</head>

<body>
	<div class="container">
		<p>
		
		<div class="row">
			<form id="logoDFEDFutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input class="right-link normal-link"
					type="submit" value="logout!" />
			</form>
		</div>
		
		
		<h1>Welcome, ${loggedInUser.firstName} ${loggedInUser.lastName } <span style="font-weight:normal; font-size:75%">(id:  ${loggedInUser.id })</span>  </h1>
	
		<table class="tablestyle">
			<caption>
				<h3>
					Your Ranches
				</h3>
			</caption>
			<thead>
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Description</th>
					<th>Subscribers</th>
					<th>Change</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${horseRanchesThisOwner}" var="horseRanch" varStatus="loopCounter">
					<tr>
						<td>${loopCounter.count }</td>
						<td>
						<a href="/owners/auth/ranches/${horseRanch.id}">${horseRanch.ranchName}"</a>--id:  ${horseRanch.id}
						</td>
						<td>${horseRanch.ranchDescription}</td>
						<td>${horseRanch.getSubscriberSize() }</td>
						<td>
							<a href="/ranches/${horseRanch.id}/edit">Update/Modify</a>
						</td>
						<td>
							<form:form action="/ranches/${horseRanch.id}" method="POST">
								<input type="hidden" name="_method" value="delete">
								<input class="normal-link" type="submit" value="Delete">
							</form:form>
						</td>
				
				
				
					</tr>
				</c:forEach>
			
			
			
			
			
			</tbody>
		</table>
	
			<div class="row">
			<a href="/ranches/new"><button>Create Horse Ranch</button></a>
		
		</div>
	
	
		<div class="row">
			<div class="col-25">
				<a href="/browsers/users/auth">My Personal Page</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
	
	</div><!-- end container -->


</body>
</html>