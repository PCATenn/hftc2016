<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
<title>Manage users | PCAT Inventory Management</title>

<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css" />
<script
	src="http://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
	var url = "listAllUsers";

	$(document).ready(function(){
	    $('#dataTable').DataTable( {
	        "ajax": {
	            "url": url,
	            "dataSrc": ""
	        },
	        "columns": [
				{ "data": "id" },
	            { "data": "firstName" },
	            { "data": "lastName" },
	            { "data": "email" },
	            { "data": "role" },
	            { "data": "supervisor" },
	            { "data": "supervisoremail" },
	            { "render": function(data, type, row, meta) {

	                  return '<input type="hidden" name="userId"><a href="gotoComplete?id=' + row.id + '">Update/Delete</a>';
	              }
	            }

	        ]
	    } );
	});

	function dothis(parameter) {
	 if(parameter == 'add') {
		 window.location.href="addUserPage";
	 }

	}

</script>
</head>
<body>

	<div class="content">
		<form>
				<input type="hidden" name="id" id="id" value="3"/>
				<input type="hidden" name="productName" id="productName" value="Car Seat"/>
				<input type="hidden" name="productDesc" id="productDesc" value="Infant"/>
				<input type="hidden" name="location" id="location" value="Nashville"/>
				<input type="hidden" name="totalInventory" id="totalInventory" value="3"/>
				<input type="hidden" name="userId" id="userId" value="${user.id}"/>
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
					<h1>Manage users</h1>


					<table id="dataTable" class="dummy-inventory">
						<thead>
							<tr>
								<th >User Id</th>
								<th >First Name</th>
								<th >Last Name</th>
								<th >Email</th>
								<th >Role</th>
								<th >Supervisor</th>
								<th >Supervisor Email</th>
								<th ></th>
							</tr>
						</thead>
					</table>

          <button name="add-user" class="button medium-button affirmative" onclick="dothis('add')">
              Add a user
          </button>
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
