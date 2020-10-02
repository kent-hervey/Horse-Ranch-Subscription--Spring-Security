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
<title>Your Ranch Details</title>

</head>

<body>
	<div class="container">
		<h1>
			Visibility:  ADMN, OWNER
		</h1>
		<div class="row">
			<form id="logoDFEDFutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input class="right-link normal-link"
					type="submit" value="logout!" />
			</form>
		</div><!-- end top row for logout -->
	
	
		<div class="row">
				<h2>Welcome ${loggedInUser.firstName }, id:  ${loggedInUser.id }</h2>
		</div><!--  end top row for Welcome -->
	
		<div class="row divBorder"><!-- div right and left columns -->
			<div class="col-50" style="padding:26px; background-color: #f0f0ff;"><!-- begin left column -->
				<h2>Horse Ranch: ${horseRanch.ranchName}, id:  ${horseRanch.id}</h2>
				<div class = "divBorder" style="height: 200px; width: 90%;">
					${horseRanch.ranchDescription}
				</div>
			</div><!-- end left column -->
			
			<div class="col-50" style="padding:20px; background-color: #ddffdd"><!-- begin right column -->
				<div class="row">
					<a href="/ranches/${horseRanch.id}/edit">Update/Modify this Horse Ranch</a>
				</div>
				<div class="row">
					Subscription price: <fmt:formatNumber value="${horseRanch.annualSubscriptionPrice }" type="currency" /> 
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
					<fmt:formatDate pattern="MM/dd/yyyy" value="${horseRanch.getCreatedAt()}"/>
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
		
		
		<div class="row"  style="padding:5px; background-color: #ffcccc;"><!-- begin row for Subscriber list for this Ranch -->

        <table class="tablestyle">
        	<caption>
        		<h3>
        			Your Subscribers for this Ranch
        		</h3>
        	
        	</caption>
        	<thead>
        		<tr>
        			<th>#</th>
        			<th>First Name</th>
        			<th>Last Name</th>
        			<th>Email</th>
        			<th>Subscription Date</th>
        		
        		</tr>
        	</thead>
        	<tbody>
        		<c:forEach items="${subscribersThisRanch}" var="subscriber"  varStatus="loopCounter">

        			<tr>
        				<td>${loopCounter.count}</td>
        				<td>${subscriber.firstName}</td>
        				<td>${subscriber.lastName}
        				<td>${subscriber.email}</td>
        				<td>
								<c:forEach items="${userHorseRanchesThisRanch}" var="userSubscriber">
									<c:choose>
										<c:when test="${subscriber.id==userSubscriber.getUserSubscriber().getId()}"> 
											<fmt:formatDate pattern="MM/dd/yyyy" value="${userSubscriber.getCreatedAt()}"/>
										</c:when>
									</c:choose>
								</c:forEach>
        				</td>

        			</tr>
        		</c:forEach>
        	</tbody>
              
        </table>

		</div><!-- end row for this ranch's Subscriber list  -->
		
	
		<div class="row"><!-- begin footer div -->
			<div class="col-50">
				<a href="/ranches/owners-properties">My Ranches</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
	
	</div><!-- end container div -->


</body>
</html>