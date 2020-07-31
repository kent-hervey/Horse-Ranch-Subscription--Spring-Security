<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/styles.css" />
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
		</div>

		<table class="tablestyle">
			<thead>
				<tr>
					<th>Name</th>
					<th>Email</th>
					<th>Make Guest/Demote from Guest</th>
					<th>Make Owner/Demote from Owner</th>
					<th>Make an Admin/Delete User completely</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.id} - <a href="/user-details">${user.firstName}
								${user.lastName}</a>
						</td>
						<td>${user.email}</td>
						<td><c:choose>
								<c:when test="${false}">
								Full
							</c:when>
								<c:when test="${user.isUserGuest()}">
								Demote from Guest
																	<form action="/admins/${user.id}" method="POST">
										<input type="hidden" name="_method" value="delete"> <input
											type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input class="normal-link"
											type="submit" value="Delete">
									</form>
							</c:when>
								<c:otherwise>

								<form action="/guests/${user.id}/roles/guestRole" method="POST">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input class="normal-link"
											type="submit" value="make-guest">
									</form>
								</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${false}">
								Full
							</c:when>
								<c:when test="${user.isUserOwner()}">
								Yes/
							</c:when>
								<c:otherwise>
									<form action="/admins/demote-owner/${user.id}" method="POST">
										<input type="hidden" name="_method" value="delete"> <input
											type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input class="normal-link"
											type="submit" value="demote-owner">
									</form>
								&nbsp;
								<form action="/admins/${user.id}/roles/adminRole" method="POST">
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
								Yes/
							</c:when>
								<c:otherwise>
									<form action="/admins/${user.id}" method="POST">
										<input type="hidden" name="_method" value="delete"> <input
											type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input class="normal-link"
											type="submit" value="Delete">
									</form>
									&nbsp;
									<form action="/admins/${user.id}/roles/adminRole" method="POST">
										<input type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" /> <input class="normal-link"
											type="submit" value="make-admin">
									</form>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>




	</div>
	<!--end container-->


</body>
</html>