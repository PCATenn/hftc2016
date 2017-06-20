<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="img/PCA-Logo_TN_2C_sm_ico.png">
<script src="vendor/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="vendor/jquery.validate.min.js"></script>
<script type="text/javascript" src="vendor/additional-methods.min.js"></script>
<link rel="stylesheet" type="text/css" href="vendor/datatables.min.css" />
<script type="text/javascript" src="vendor/datatables.min.js"></script>

<script src="request.js"></script>
<link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
<title>Request an item | PCAT inventory</title>
</head>

<body>
	<div class="content">
		<header>
			<a href="http://www.pcat.org/">
				<img src="img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo">
			</a>
		<c:url var="logoutUrl" value="/login?logout"/>
		<form action="${logoutUrl}"  method="post">
			<input type="submit" name="logout" class="button logout-button neutral" value="Log out"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
		</header>

		<section>
	      <nav id="nav-bar">
	        <ul>
	          <li>                    <a href="request.jsp">              request an item     </a>    </li>
	        <sec:authorize access="hasRole('SUPERVISOR')">
	          <li>                    <a href="review-approvals.jsp">     review approvals    </a>    </li>
	        </sec:authorize>
	        <sec:authorize access="hasRole('ADMINISTRATOR')">
	          <li id="manage-items">  <a href="listAllInventories.jsp">   manage items        </a>    </li>
	          <li id="manage-users">  <a href="listAllUsers.jsp">         manage users        </a>    </li>
	        </sec:authorize>
	        </ul>
	      </nav>
			<div class="section-body">
				<h1>Request an item from our inventory</h1>

				<form action="foo" id="request-items" style="padding: 0 5rem;">

					<input type="hidden" name="userId" value="${user.id}"/>

					<div class="inline-directive">
						<h2>Enter the family number:</h2>

						<label><input type="text" name="familyNumber" class="input-field">
							&nbsp &nbsp i.e. &nbsp(XXXX-0000)
                        </label>
					</div>

					<h2 style="margin: -2rem 5rem 3rem 5rem">
						Request an item by entering a quantity <br>and clicking the
						Request button for an item:
					</h2>

					<div style="padding: 0 5rem;">
						<table id="dataTable" name="dataTable">
							<thead>
								<tr>
									<th>Item</th>
									<th>Description</th>
									<th>Location</th>
									<th>Available<br/>Quantity</th>
									<th>Requested<br/>Quantity</th>
									<th>Action</th>
								</tr>
							</thead>
						</table>
					</div>
					<input type="hidden" id="_csrf_name" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</form>
			</div>
		</section>
	</div>

    <footer>
        Prevent Child Abuse Tennessee
    </footer>

</body>
</html>
