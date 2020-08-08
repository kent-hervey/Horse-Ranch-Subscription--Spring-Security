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
<title>Your Property Details</title>

</head>

<body>
	<div class="container">
		<h1>
			Visibility:  ADMN, OWNER
		</h1>
		<h2>
		
Details on specific property for use by the owner of that property
	//>>>Needs id added to make specific property
		
		</h2>
	
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
			
			
			
			</div><!-- end right column -->
	
		</div><!-- end row covering left and right columns of content -->
		
		
		<div class="row"  style="padding:5px; background-color: #ffcccc;"><!-- begin row for Subscriber list for this property -->
		asdf
		asdf
		as
		f
		asfd
		sa
		df
		
		</div><!-- end row for this property's Subscriber list  -->
		
	
		<div class="row"><!-- begin footer div -->
			<div class="col-60">
				<a href="/ranches/owners-properties">See your owners page...if you are an owner</a>
			</div>
			<div class="col-25">
				<a href="/ranches/property-details">Property Details</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>
	
	</div><!-- end container div -->
	
	
	
	</div>

</body>
</html>