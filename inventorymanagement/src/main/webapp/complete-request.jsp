<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>Complete a request | PCAT Inventory Management</title>
</head>
<body>

    <div class="content">
        <form action="submitForRequestApproval" method="post">
            <input type="hidden" name="userId" id="userId" value="<%=request.getParameter("userId") %>"/>
            <input type="hidden" name="inventoryId" id="inventoryId" value="${inventory.id}"/>
            <header>
                <a href="http://www.pcat.org/">
                    <img src="img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo">
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
                        Complete your request
                    </h1>

                    <div class="inline-directive">
                        <h2>
                            Family number:
                        </h2>

                        <label><input type="text" class="input-field" name="familyId" id="familyId"></label>
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

                            <div class="dummy-inventory-row">
                                <div class="dummy-qty dummy-table-row confirm"><input type="text" class="input-field" name="quantity" id="quantity"></div>
                                <div class="dummy-prod dummy-table-row">${inventory.productName}</div>
                                <div class="dummy-desc dummy-table-row">${inventory.productDesc}</div>
                                <div class="dummy-loc dummy-table-row">${inventory.location}</div>
                            </div>
                        </div>
                    </div>

                    <h3 style="margin: 4rem 8rem 2rem 8rem;">
                        To remove an item from the list, set quantity to 0 (zero) before submitting.
                    </h3>

                    <div>
                        <button name="cancel-request" class="button medium-button neutral">
                            Start over
                        </button>
                        <button name="submit-request" class="button medium-button affirmative">
                            Submit for approval
                        </button>
                    </div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
            </section>
        </form>
    </div>

    <footer>
        Prevent Child Abuse Tennessee
    </footer>

</body>
</html>
