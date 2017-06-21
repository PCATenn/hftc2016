<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>Request approved | PCAT Inventory Management</title>
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
                <h2>
                    Request approved.
                </h2>

                <h3>
                    You’ll receive an email confirming your approval of the request.
                </h3>

                <button name="approvals-return" class="button medium-button affirmative" onclick="history.back(1)">
                    Return to pending approvals list
                </button>
            </div>
        </section>
    </div>

    <footer>
        Prevent Child Abuse Tennessee
    </footer>

</body>
</html>
