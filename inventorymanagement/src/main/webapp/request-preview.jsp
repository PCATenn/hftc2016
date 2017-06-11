<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/png" href="../img/PCA-Logo_TN_2C_sm_ico.png" >
    <script src="../vendor/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../vendor/datatables.min.css" />
    <link rel="stylesheet" type="text/css" href="../css/app.css" media="screen" />
    <title>Complete a request | PCAT Inventory Management</title>
</head>
<body>
	<c:url var="returnUrl" value="/request.jsp"/>
	<c:url var="completeRequestUrl" value="complete"/>
    <div class="content">
		<header>
	        <a href="http://www.pcat.org/">
	            <img src="../img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo" />
	        </a>
			<c:url var="logoutUrl" value="/login?logout"/>
			<form action="${logoutUrl}"  method="post">
				<input type="submit" name="logout" class="button logout-button neutral" value="Log Out"/>
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
	        <h1>
	            Complete your request
	        </h1>
			<form action="submitForapproval" id="request-items" style="padding: 0 5rem;">
				<div class="inline-directive">
			    	<h2>
			        	Family number:
			    	</h2>

    				<label><input readonly="readonly"  type="text" class="input-field" name="familyId" id="familyId" value="${familyId}"/></label>
				</div>
				<div class="item-box">
				    <h4>
				        Items You’re requesting:
				    </h4>
				    <div class="dummy-inventory">
				        <div class="dummy-inventory-label-row">
				            <p style="padding-left: 0;">Qty</p>
				       		<p style="padding-left: 1.75rem;">Product #</p>
				       		<p style="padding-left: 2.75rem;">Description</p>
				       		<p style="padding-left: 14.25rem;">Location</p>
				       	</div>
				   </div>
					<div class="dummy-inventory-row">
						<div class="dummy-qty dummy-table-row confirm">
							<input readonly="readonly" type="text" class="input-field" name="quantity" id="quantity" value="${quantity}">
						</div>
						<div class="dummy-prod dummy-table-row">
							<input readonly="readonly" type="text" class="input-field" name="productName" id="productName" value="${productName}">
						</div>
						<div class="dummy-desc dummy-table-row">
							<input readonly="readonly" type="text" class="input-field" name="productDesc" id="productDesc" value="${productDesc}">
						</div>
						<div class="dummy-loc dummy-table-row">
							<input readonly="readonly" type="text" class="input-field" name="location" id="location" value="${location}">
						</div>
						<div>
							<input type="hidden" class="input-field" name="inventoryId" id="inventoryId" value="${inventoryId}">
						</div>
					</div>
					<div>
						<button name="cancel-request" class="button medium-button neutral" formaction="${returnUrl}">
							Start over
						</button>
						<button name="submit-request" class="button medium-button affirmative" formaction="${completeRequestUrl}" formmethod="POST">
							Submit for approval
						</button>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</div>
        	</form>
    	</div>
        </section>
	</div>
    <footer>
        Prevent Child Abuse Tennessee
    </footer>
</body>
</html>
