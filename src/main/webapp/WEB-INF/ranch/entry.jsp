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
<title>Ranch Properties Service</title>
</head>
<body>

	<div class="container">

	<div class="row">
		<h1>Welcome to Ranch Properties Service</h1>
	</div>
		<div class= "divBorder">
			<div class="row">
				<div class="col-5">
				</div>
				<div class="col-90">
					<h4>
						Horse Ranch Community is a brokerage service for equestrians connecting enthusiasts with owners of horse ranches available for subscription.
					</h4>
					<div class="padding-top-5px"></div>
					<h4>
						Sign up here and apply to automatically be assigned the browser role, and request access as one or both of these roles:
					</h4>
				</div>
			</div>
			<div class="row">
				<div class="col-10">
				</div>
				<div class="col-75">
					<h5>Guest:  Looking to subscribe or have already subscribe to one or more ranches</h5>
					<h5>Owner:  Have one or more ranches to be offered on a subscription basis</h5>
				</div>
			</div>
		</div><!-- end divBorder -->
		<div>
		<img src="${pageContext.request.contextPath}/images/sampleranchpict.jpg" width="700" height="400"/>
		
		</div> 
		<div class="row"><!--  start footer -->

			<div class="col-20">
				<a href="javascript:history.back()">Previous Page</a>
			</div>

			<div class="col-20">
				<a href="/loginreg">Register/Login</a>
			</div>
			
		</div> <!--  end footer -->
		

	</div> <!-- End container -->


</body>
</html>