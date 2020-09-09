<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/styles2.css"/>
<title>Admin's view of each user</title>
<!-- gives Admin detailed view of any user -->
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
		<h1>Welcome Admin ${currentUser.firstName} &nbsp; ${currentUser.lastName }</h1>
		<h2>Note:  This page is only for use by Admins</h2>
	</div>
	
		<div class= "divBorder">
			<div class="row">
				<div class="col-50">
					<h2>User Id:</h2>
				</div>
				<div class="col-50">
					<h2>${selectedUser.id}</h2>
				</div>		
			</div>
			
			
			<div class="row">
				<div class="col-50">
					<h2>Email: </h2>
				</div>
				<div class="col-50">
					<h2>${selectedUser.email}</h2>
				</div>		
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Sign up date: </h2>
				</div>
				<div class="col-50">
					<h2><fmt:formatDate pattern="MMM dd, yyyy" value="${selectedUser.createdAt}" /></h2>
				</div>		
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Last Sign in: </h2>
				</div>
				<div class="col-50">
					<h2>
					<fmt:formatDate pattern="MMM dd, yyyy" value="${selectedUser.lastSignIn}" /><!-- was "MMM dd, yyyy hhh mmm ssss" -->
					</h2>
				</div>		
			</div>
			
			<div class="row">
				<div class="col-50">
					<h2>Assigned Roles:</h3>
					<ul>
					<c:if test="${selectedUserNumRoles>0}">
						 <c:forEach items="${selectedUser.roles}" var="roles">
						 	<li>
						 		${roles.getName()}
						 		
						 	</li>
						</c:forEach>
					</c:if>
					</ul>
					<c:if test="${selectedUserNumRoles==0}">
						None
					</c:if>
					
				</div>
			</div>
			
	
		</div><!-- end divBorder -->
		
		<div class="row">
			<div class="col-25">
				<a href="/admin">Admin page (just for admins)</a>
			</div>
			<div class="col-25">
				<a href="/user-details">My Personal Page</a>
			</div>
			<div class="col-25">
				<a href="javascript:history.back()">Previous Page</a>
			</div>
		</div>

	</div><!--end container-->

</body>
</html> 