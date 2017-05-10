<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>Update an item | PCAT Inventory Management</title>
    <script src="http://code.jquery.com/jquery-1.12.4.min.js" ></script>
</head>
<body>

    <div class="content">
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

            <div class="section-body">
                <h1>
                    Update an item
                </h1>

                <form style="margin: -3rem 5rem 3rem 5rem;">
                    <div class="add-form">
                        <label for="updateProdName">
                            Product name:
                        </label>
                        <input type="text" id="productName" name="productName" class="input-field" style="width: 20rem;" value="${inventory.productName }"/>
                    </div>

	private String location; <br/> ${inventory.location}

                    <div class="add-form">
                        <label for="updateProdDescription">
                            Product Description:
                        </label>
                        <input type="text" id="productDesc" name="productDesc" class="input-field" style="width: 20rem;" value="${inventory.productDesc}"/>
                    </div>

                    <div class="add-form">
                        <label for="updateProdQuantity">
                            Quantity:
                        </label>
                        <input type="number" id="totalInventory" name="totalInventory" class="input-field" style="width: 5rem;" value="${inventory.totalInventory}"/>
                    </div>
                   <div class="add-form">
                        <label for="updateProdQuantity">
                            Reserved Quantity:
                        </label>
                        &nbsp ${reservedInventory}
                        <input type="number" id="reservedInventory" name="reservedInventory" class="input-field" style="width: 5rem;" value="${inventory.reservedInventory}"/>
                    </div>
                    <div class="add-form">
                        <label for="updateProdLocation">
                            Location:
                        </label>
                        <select id="location" name="location" style="min-width: 16rem;"  >
                            <option>Nashville</option>
                            <option>Chattanooga</option>
                            <option>Oak Ridge</option>
                        </select>
                   </div>

                    <div style="margin-top: 3rem; display: flex; justify-content: center;">
                        <button type="reset" name="cancel-request" class="button medium-button neutral">
                            Cancel
                        </button>

                        <button type="submit" name="delete-item" class="button medium-button danger">
                            Delete this item
                        </button>

                        <button type="submit" name="submit-request" class="button medium-button affirmative">
                            Update this item
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
<script type="text/javascript">
	$(document).ready(function() {
		console.log("attempting to update the select list with ${inventory.location}");
		$('#location').val('${inventory.location}');
	});

</script>

</html>
