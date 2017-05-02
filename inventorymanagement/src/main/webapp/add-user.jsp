<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>Add a user | PCAT Inventory Management</title>
</head>
<body>

    <div class="content">
        <form action="addUser">
            <header>
                <a href="http://www.pcat.org/">
                    <img src="img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo">
                </a>

                <button name="logout" class="button logout-button neutral">
                    Log out
                </button>
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
			
                <script type="text/javascript">
                    // when able to access user,
                    //   1. replace the condition (currently a string) in each if statement below with the logic it describes
                    //      (currently the conditions both evaluate to true since any string except "" evaluates to true)
                    //   2. uncomment the body of each if statement
                    //   3. feel free to delete this comment block

                    if ('user.role === homeVisitor') {
                        // document.getElementById("nav-bar").classList.add("hidden");
                    } else if ('user.role === supervisor') {
                        // document.getElementById("manage-items").classList.add("hidden");
                        // document.getElementById("manage-users").classList.add("hidden");
                    }
                </script>

                <div class="section-body">
                    <h1>
                        Add a user
                    </h1>

                    <form style="margin: -3rem 5rem 3rem 5rem;">
                        <div class="add-form">
                            <label for="firstName">
                                First name:
                            </label>
                            <input type="text" id="firstName" name="firstName" class="input-field" style="width: 15rem;" />
                        </div>

                        <div class="add-form">
                            <label for="lastName">
                                Last name:
                            </label>
                            <input type="text" id="lastName" name="lastName" class="input-field" style="width: 15rem;" />
                        </div>

                        <div class="add-form">
                            <label for="userEmail">
                                Email address:
                            </label>
                            <input type="email" id="userEmail" name="userEmail" class="input-field" style="width: 19rem;" />
                        </div>

                        <div class="add-form">
                            <label for="userRole">
                                Role:
                            </label>
                            <select id="userRole" name="userRole" class="dropdown" style="min-width: 14rem;" >
                                <option value="visitor" selected>Home Visitor</option>
                                <option value="supervisor">Supervisor</option>
                                <option value="admin">User Admin</option>
                            </select>
                        </div>

                        <div class="add-form">
                            <label for="supervisorName">
                                Supervisor:
                            </label>
                            <select id="supervisorName" name="supervisorName" class="dropdown" style="min-width: 18rem;" >
                                <option value="value1" selected>Bonnie Fernandez</option>
                                <option value="value2">Cindie Lou Hoo</option>
                                <option value="value3">Jennifer Caudle</option>
                            </select>
                        </div>
                        <!--<div class="add-form">-->
                            <!--<label for="supervisorEmail">-->
                                <!--Supervisor’s email address:-->
                            <!--</label>-->
                            <!--<input type="email" id="supervisorEmail" name="supervisorEmail" class="input-field" style="width: 20rem;" />-->
                        <!--</div>-->

                        <div style="margin-top: 3rem; display: flex; justify-content: center;">
                            <button type="reset" name="cancel-request" class="button medium-button neutral">
                                Cancel
                            </button>

                            <button type="submit" name="submit-request" class="button medium-button affirmative">
                                Add this user
                            </button>
                        </div>
                    </form>
                </div>
            </section>
        </form>
    </div>

    <footer>
        Prevent Child Abuse Tennessee
    </footer>

</body>
</html>
