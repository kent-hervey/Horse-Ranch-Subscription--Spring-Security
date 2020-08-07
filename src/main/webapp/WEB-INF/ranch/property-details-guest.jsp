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
<title>Property Details</title>

</head>

<body>
	<div class="container">
		<h1>
			Visibility:  ADMN, GUEST
		</h1>
		<h2>	
			Provides details on specific property in hope guest will click the subscribe button and spend some money
				//>>>Needs id added to make specific property
		</h2>
		
		<div class="row">
			<form id="logoDFEDFutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input class="right-link normal-link"
					type="submit" value="logout!" />
			</form>
		</div>
		

		<div class="row">
				Welcome ${loggedInUser.firstName }, id:  ${loggedInUser.id }
		</div>

		<div class="row">
			<div class="col-50" style="padding:26px; background-color: #f0f0ff;"    >
				<h2>Horse Ranch: ${horseRanch.ranchName}, id:  ${horseRanch.id}</h2>
				<div class = "divBorder" style="height: 200px; width: 90%;">
					${horseRanch.ranchDescription}
				</div>
			</div>
	
			<div class="col-50" style="padding:20px; background-color: #c0c0ff">

				<h2>
							<c:choose>
								<c:when test = "${horseRanch.isUserSubscribingThisRanch(loggedInUser) }">
									<form:form action="/ranches/${horseRanch.id}/users" method="POST">
										<input type="hidden" name="_method" value="delete">
										<button>Un-Subscribe</button>
									</form:form>
								</c:when>
								<c:otherwise>
									<form:form action="/ranches/${horseRanch.id}/users" method="POST">
										<button>Subscribe</button>
									</form:form>
								</c:otherwise>
							</c:choose>

				</h2>
				<div class="row">
					<h5>Owner</h5>
						<Ul>
							<li>Name:  ${horseRanch.ranchOwner.firstName} ${horseRanch.ranchOwner.lastName}
							<li>Email: ${horseRanch.ranchOwner.email }
						</Ul>

				</div>
				<div class="row">
					Current Subscriber Count:  ${horseRanch.getSubscriberSize()}
				</div>		
				<div class="row">
					Date First Listed:  ${horseRanch.createdAt }
				</div>		
				<div class="row">
					Latest Update:  ${horseRanch.updatedAt}
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
		
		
		
		
			</div>
		
		
		
		</div>

	
	
	
	
	
	
	
	
	
	
	
	
	
	</div><!-- end container div -->

</body>
</html>