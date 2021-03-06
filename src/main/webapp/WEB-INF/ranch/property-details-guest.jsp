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
<title>Ranch Details for Guests</title>

</head>

<body>
	<div class="container">

		<div class="row">
			<form id="logoDFEDFutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input class="right-link normal-link"
					type="submit" value="logout!" />
			</form>
		</div><!-- end top row for logout -->
		

		<div class="row">
				<h2>Welcome Guest: ${loggedInUser.firstName } <span style="font-weight:normal; font-size:75%">(id:  ${loggedInUser.id })</span></h2>
		</div><!--  end top row for Welcome -->

		<div class="row"><!-- div right and left columns -->
			<div class="col-50" style="padding:26px; background-color: #f0f0ff;"><!-- begin left column -->
				<h2>Ranch: ${horseRanch.ranchName}</h2>
				<div class = "divBorder" style="height: 200px; width: 90%;">
					${horseRanch.ranchDescription}
				</div>
			</div><!-- end left column -->
	
			<div class="col-50" style="padding:20px; background-color: #c0c0ff"><!-- begin right column -->
				<div class="row">
					
							<c:choose>
								<c:when test = "${horseRanch.isUserSubscribingThisRanch(loggedInUser) }">
									<form:form action="/guest-auth/ranches/${horseRanch.id}/users/auth" method="POST">
										<input type="hidden" name="_method" value="delete">
										<button>Un-Subscribe</button>
									</form:form>
								</c:when>
								<c:otherwise>
									<form:form action="/guest-auth/ranches/${horseRanch.id}/users/auth" method="POST">
										<button>Subscribe</button>
									</form:form>
									At price: <fmt:formatNumber value="${horseRanch.annualSubscriptionPrice }" type="currency" /> 
								</c:otherwise>
							</c:choose>

				</div>
				
				<div class="row">
					Ranch ID:    ${horseRanch.getId()}
				</div>	
				
								<div class="row">
					<h5>Owner</h5>
						<Ul>
							<li>Name:  ${horseRanch.ranchOwner.firstName} ${horseRanch.ranchOwner.lastName}
							<li>Email: ${horseRanch.ranchOwner.email }
							<li>Owner ID: ${horseRanch.ranchOwner.id}
						</Ul>

				</div>
				
				
				<div class="row">
					Current Subscriber Count:  ${horseRanch.getSubscriberSize()}
				</div>		
				<div class="row">
					Date First Listed:  
					<fmt:formatDate pattern="MM/dd/yyyy" value="${horseRanch.getCreatedAt()}"/>
				</div>		
				<div class="row">
					Latest Update:  
					<fmt:formatDate pattern="MM/dd/yyyy" value="${horseRanch.getUpdatedAt()}"/>
				</div>		
				<div class="row">
					Location:  ${horseRanch.location}
				</div>		
				<div class="row">
					Size (acres):  ${horseRanch.numberAcres}
				</div>		
				<div class="row">
					Accommodations
						<ul>
							<li>Number of People:  ${horseRanch.peopleCapacity}
							<li>Number of Horses: ${horseRanch.horseCapacity}
						</ul>
				</div>				

			</div><!-- end right column -->
		
		</div><!-- end row covering left and right columns of content -->

	
		<div class="row"><!-- begin footer div -->
			<div class="col-25">
				<a href="/browsers/users/auth">My Personal Page</a>
			</div>
			<div class="col-25">
				<a href="/guest-auth/ranches">Horse Ranch List</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
	
	
	
	
	
	
	
	
	
	
	
	</div><!-- end container div -->

</body>
</html>