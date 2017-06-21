<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>Update a user | PCAT Inventory Management</title>
</head>

<body>

    <div class="content">
        <form action="updateUser">
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
                    <h1>
                        Update a user
                    </h1>

                    <form style="margin: -3rem 5rem 3rem 5rem;">
                        <div class="add-form">
                            <label for="updateFirstName">
                                First name:
                            </label>
                            <input type="text" id="updateFirstName" name="firstName" class="input-field" style="width: 15rem;" />
                        </div>

                        <div class="add-form">
                            <label for="updateLastName">
                                Last name:
                            </label>
                            <input type="text" id="updateLastName" name="lastName" class="input-field" style="width: 15rem;" />
                        </div>

                        <div class="add-form">
                            <label for="updateUserEmail">
                                Email address:
                            </label>
                            <input type="email" id="updateUserEmail" name="userEmail" class="input-field" style="width: 19rem;" />
                        </div>

                        <div class="add-form">
                            <label for="updateUserRole">
                                Role:
                            </label>
                            <select id="updateUserRole" name="userRole" style="min-width: 14rem;" >
                                <option value="visitor" selected>Home Visitor</option>
                                <option value="supervisor">Supervisor</option>
                                <option value="admin">User Admin</option>
                            </select>
                        </div>

                        <div class="add-form">
                            <label for="updateSupervisorName">
                                Supervisor:
                            </label>
                            <select id="updateSupervisorName" name="supervisorName" style="min-width: 18rem;" >
                                <option value="value1" selected>Bonnie Fernandez</option>
                                <option value="value2">Cindie Lou Hoo</option>
                                <option value="value3">Jennifer Caudle</option>
                            </select>
                        </div>
                        <!--<div class="add-form">-->
                            <!--<label for="updateSupervisorEmail">-->
                                <!--Supervisor’s email address:-->
                            <!--</label>-->
                            <!--<input type="email" id="updateSupervisorEmail" name="supervisorEmail" class="input-field" style="width: 20rem;" />-->
                        <!--</div>-->

                        <div style="margin-top: 3rem; display: flex; justify-content: center;">
                            <button type="reset" name="cancel-request" class="button medium-button neutral">
                                Cancel
                            </button>

                            <button type="submit" name="delete-user" class="button medium-button danger">
                                Delete this user
                            </button>

                            <button type="submit" name="submit-request" class="button medium-button affirmative">
                                Update this user
                            </button>
                        </div>
                    </form>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
            </section>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>

    <footer>
        Prevent Child Abuse Tennessee
    </footer>

</body>
</html>
