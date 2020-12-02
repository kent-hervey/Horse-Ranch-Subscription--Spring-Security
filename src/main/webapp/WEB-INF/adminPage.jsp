<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/styles2.css" />
<title>Admin Dash</title>
</head>
<body>
	<div class="container">

		<div class="row">
			<form id="logoDFEDFutForm" method="POST" action="/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /> <input class="right-link normal-link"
					type="submit" value="logout!" />
			</form>
		</div>

		<div class="row">
			<h1>Welcome Admin ${currentUser.firstName}</h1>
			<h4><a href="#roleDefinitions">Jump to Role Definitions</a></h4>
		</div>

		<table class="tablestyle">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Make Guest/Demote from Guest</th>
					<th>Make Owner/Demote from Owner</th>
					<th>Make an Admin/Delete User completely</th>
					<th>User's note to Admin</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.id}- <a href="/users/${user.id}">${user.firstName}
								${user.lastName}</a>
						</td>
						<td>${user.email}</td>
						<td><c:choose>
								<c:when test="${user.isUserGuest()}">

									<form action="/users/${user.id}/roles/role-guest" method="POST">
											<input type="hidden" name="_method" value="delete"> <input
												type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" /> <input class="normal-link"
												type="submit" value="demote-guest">
									</form>
								</c:when>
								<c:otherwise>

									<form action="/users/${user.id}/roles/role-guest" method="POST">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input class="normal-link"
											type="submit" value="make-guest">
									</form>
								</c:otherwise>
							</c:choose></td>
						<td><c:choose>

								<c:when test="${user.isUserOwner()}">
								
									<form action="/users/${user.id}/roles/role-owner" method="POST">
										<input type="hidden" name="_method" value="delete"> 
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
										<input class="normal-link" type="submit" value="demote-owner">
									</form>
								</c:when>
								<c:otherwise>

								&nbsp;
								<form action="/users/${user.id}/roles/role-owner" method="POST">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input class="normal-link"
											type="submit" value="make-owner">
									</form>
								</c:otherwise>
							</c:choose></td>

						<td><c:choose> 
								<c:when test="${false}">
								Full
							</c:when>
								<c:when test="${user.isUserAdmin()}">
									Is Admin
							</c:when>
								<c:otherwise>
									<form action="/users/${user.id}" method="POST">
										<input type="hidden" name="_method" value="delete"> <input
											type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input class="normal-link"
											type="submit" value="Delete">
									</form>
									&nbsp;
									<form action="/users/${user.id}/roles/role-admin" method="POST">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input class="normal-link"
											type="submit" value="make-admin">
									</form>
								</c:otherwise>
							</c:choose></td>
							<td>
								${fn:substring(user.noteToAdmin, 0,30)}
							</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="row">
			<div class="col-25">
				<a href="/browsers/users/auth">My Personal Page</a>
			</div>

			</div>
			

<h2 id="roleDefinitions">Role Definitions Below</h2>
	<table class="tablestyle">
		<thead>
			<tr>
				<th>Role Name</th>
				<th>Role Explanation</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${roles}" var = "role">
				<tr>
					<td>
						${role.name}
					</td>
					<td>
						${role.roleExplanation}
					</td>
				</tr>
			</c:forEach>
		
		</tbody>
	
	
	</table>



	</div>
	<!--end container-->


</body>
</html>