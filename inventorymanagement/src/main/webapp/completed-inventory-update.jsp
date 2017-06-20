<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>Item updated | PCAT Inventory Management</title>
</head>

<body>
	<c:url var="returnUrl" value="/listAllInventories.jsp"/>
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
                    Item updated.
                </h2>
                    <div class="add-form">
                        <label for="updateProdName">
                            Product name:
                        </label>
                        <span class="input-field" style="width: 20rem;">${inventory.productName }</span>
                    </div>
                    <div class="add-form">
                        <label for="updateProdDescription">
                            Product Description:
                        </label>
                        <span class="input-field" style="width: 20rem;" >${inventory.productDesc}</span>
                    </div>

                    <div class="add-form">
                        <label for="updateProdQuantity">
                            Quantity:
                        </label>
                        <span class="input-field" style="width: 5rem;">${inventory.totalInventory}"</span>
                    </div>
                   <div class="add-form">
                        <label for="updateProdQuantity">
                            Reserved Quantity:
                        </label>
                       <span class="input-field" style="width: 5rem;">${inventory.reservedInventory}</span>
                    </div>
                    <div class="add-form">
                        <label for="updateProdLocation">
                            Location:
                        </label>
                       <span class="input-field" style="width: 5rem;">${inventory.location}</span>
                        
                   </div>
                   <form>
 				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" name="inventory-return" style="margin-top: 3rem;" class="button medium-button affirmative" formaction="${returnUrl}">
                    Return to inventory list
                </button>
                </form>
            </div>
        </section>
    </div>

    <footer>
        Prevent Child Abuse Tennessee
    </footer>

</body>
</html>
