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
<title>${currentUser.firstName}: Personal Page</title>
</head>
<body>
	<div class="container">
	
		<div class="row">
			<form id="logoDFEDFutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input class="right-link normal-link" type="submit" value="logout!" />
			</form>
		</div>

	<div class="row">
		<h1>Welcome ${currentUser.firstName}&nbsp;${currentUser.lastName }; Here is your personal information.</h1>
	</div>
	
		<div class= "divBorder">
			<div class="row">
				<div class="col-50">
					<h2>User Id:</h2>
				</div>
				<div class="col-50">
					<h2>${currentUser.id}</h2>
				</div>		
			</div>
			
			
			<div class="row">
				<div class="col-50">
					<h2>Email: </h2>
				</div>
				<div class="col-50">
					<h2>${currentUser.email}</h2>
				</div>		
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Sign up date: </h2>
				</div>
				<div class="col-50">
					<h2><fmt:formatDate pattern="MMM dd, yyyy" value="${currentUser.createdAt}" /></h2>
				</div>		
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Last Sign in: </h2>
				</div>
				<div class="col-50">
					<h2>
					<fmt:formatDate pattern="MMM dd, yyyy" value="${currentUser.lastSignIn}" /><!-- was "MMM dd, yyyy hhh mmm ssss" -->
					</h2>
				</div>		
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Your Roles:</h3>
					<ul>
					 <c:forEach items="${currentUser.roles}" var="roles">
					 	<li>
					 		${roles.getName()}
					 	</li>
					</c:forEach>
					</ul>
				</div>
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Your Message to Admin:</h2>
						<ul>
							<li>
								<h4>${currentUser.noteToAdmin}</h4>
							</li>
						</ul>
				</div>
			</div>
			
			
			
	
		</div><!-- end divBorder -->
		
		<div class="row">
			<security:authorize access="hasAnyRole('ADMIN','OWNER')">
				<div class="col-20">
					<a href="/ranches/owners-properties">Ranches You Own</a><!-- for Owner s ownersPage.jsp -->
				</div>
			</security:authorize>
			<security:authorize access="hasAnyRole('ADMIN','GUEST')">
				<div class="col-20">
					<a href="/ranches/property-list">All Ranch Listings</a><!--for Guest s property-list.jsp -->
				</div>
			</security:authorize>
			<security:authorize access="hasRole('BROWSER')">
				<div class="col-20">
					<a href="/ranches/entry">Entry Page</a><!--for BROWSER s entry.jsp -->
				</div>
			</security:authorize>
			<security:authorize access="hasRole('ADMIN')">
				<div class="col-20">
					<a href="/admin">Admin Page</a><!--for ADMIN s only adminPage.jsp -->
				</div>
			</security:authorize>
			<div class="col-20">
				<a href="javascript:history.back()">Previous Page</a>
			</div>

			
			
		</div>

	</div><!--end container-->

</body>
</html> 