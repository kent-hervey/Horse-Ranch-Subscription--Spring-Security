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
				<h2>Horse Ranch: ${horseRanch.ranchName}</h2>
				<div class = "divBorder" style="height: 200px; width: 90%;">
					${horseRanch.ranchDescription}
				</div>
		
		
		
		
		
		
		
		
			</div>
	
			<div class="col-50" style="padding:20px; background-color: #c0c0ff">
		 		<h1>asdfas df asdf asdf a df sdf sd fas f as dfas f as df sf sdf as df as f asdf sa df sa f sdf as df as df </h1>
		
		
		
		
		
		
		
		
		
		
			</div>
		
		
		
		</div>

	
	
	
	
	
	
	
	
	
	
	
	
	
	</div><!-- end container div -->

</body>
</html>