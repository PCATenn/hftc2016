<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>Add an item | PCAT Inventory Management</title>
</head>

<body>

    <div class="content">
        <header>
            <a href="http://www.pcat.org/inventorymanagement">
                <img src="img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo">
            </a>
            <span><h2 style="margin: 0rem 5rem 3rem 5rem">${System.getenv("PCAT_ENVIRONTMENT_DISPLAY_TEXT")}</h2></span>
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
                    Add an item
                </h1>

                <form style="margin: -3rem 5rem 3rem 5rem;">
                    <div class="add-form">
                        <label for="prodName">
                            Product name:
                        </label>
                        <input type="text" id="prodName" name="prodName" class="input-field" style="width: 20rem;" />
                    </div>

                    <div class="add-form">
                        <label for="prodDescription">
                            Product Description:
                        </label>
                        <input type="text" id="prodDescription" name="prodDescription" class="input-field" style="width: 20rem;" />
                    </div>

                    <div class="add-form">
                        <label for="prodQuantity">
                            Quantity:
                        </label>
                        <input type="number" id="prodQuantity" name="prodQuantity" class="input-field" style="width: 5rem;" />
                    </div>

                    <div class="add-form">
                        <label for="prodLocation">
                            Location:
                        </label>
                        <select id="prodLocation" name="prodLocation" style="min-width: 16rem;" >
                            <option value="nashville" selected>Nashville</option>
                            <option value="chattanooga">Chattanooga</option>
                            <option value="oak-ridge">Oak Ridge</option>
                        </select>
                    </div>

                    <div style="margin-top: 3rem; display: flex; justify-content: center;">
                        <button type="reset" name="cancel-request" class="button medium-button neutral">
                            Cancel
                        </button>

                        <button type="submit" name="submit-request" class="button medium-button affirmative">
                            Add this item
                        </button>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </div>
        </section>
    </div>

    <footer>
        Prevent Child Abuse Tennessee
    </footer>

</body>
</html>
