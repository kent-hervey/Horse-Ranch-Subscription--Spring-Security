<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="/css/styles2.css"/>
		<title>Login/Register</title>
	</head>
	<body>
		<div class="container">
		
				<div class="row">
			<div class="col-25">
				<a href="/ranches/entry">Entry Page</a>
			</div>

			</div>

			<h3 class="bad">${preLogIn}</h3>
			<h3 class="good">${logoutSuccess} ${preLoginMessage}</h3>

			<div class="row"> <!-- row covers both  Register and  Login -->
					${logoutMessage}
			
			
					<div class="row"><!-- Top Login -->
					
					<h1>Login</h1>
						
					${errorMessage}
						
					<div class="form-group">
						<form method="POST" action="/login">
							<p>
								<label for="username">Username (email)</label>
								<input type="text" id="username" name="username"/>
							</p>
							<p>
								<label  for="password">Password</label>
								<input type="password" id="password" name="password"/>
							</p>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<input type="submit" value="Login!"/>
					
						</form>

					</div><!-- end div form-group for Login-->
				</div><!-- end Top Login -->
				
				<div class="row"><!-- for bottom Register -->

					<h1>Register!</h1>				
				
					<div class="form-group">


						<!-- <p><form:errors path="user.*"/></p>-->
					
						<form:form method="POST" action="/registration" modelAttribute="user">
							<p>
								<form:label path="email">Email (will be username):</form:label>

								<form:label path="email">Email:</form:label>
								<form:errors class="bad" path="email"/>
								<form:input path="email"/>
							</p>

							<p>
								<form:label path="firstName">First Name:</form:label>
								<form:errors class="bad" path="firstName"/>
								<form:input path="firstName"/>
							</p>
														
							<p>
								<form:label path="lastName">Last Name:</form:label>
								<form:errors class="bad" path="lastName"/>
								<form:input path="lastName"/>
							</p>		
							
							<p>

								<form:label path="noteToAdmin">Include message to admin including whether you want guest privelages, owner, or both, and why:</form:label>
								<form:errors class="bad" path="noteToAdmin"/>
								<form:input path="noteToAdmin"/>
							


							
							</p>
							
							</p>
							
							<p>
								<form:label path="password">Password:</form:label>

								<form:errors class="bad" path="password"/>

								<form:password path="password"/>
							</p>
							<p>
								<form:label path="passwordConfirmation">Password Confirmation:</form:label>
								<form:errors class="bad" path="passwordConfirmation"/>
								<form:password path="passwordConfirmation"/>
							</p>
							
							
							
							
							
							
							<input type="submit" value="Register!"/>
						</form:form>
					
					
					
					
					</div>
				</div><!-- end Bottom Register -->
				


			</div><!-- end row that covers both  Register and  Login -->
		</div><!-- End container -->
	
	</body>
</html>