<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
		          <li id="manage-users" class="current-link">  <a href="listAllUsers.jsp">         manage users        </a>    </li>
		        </sec:authorize>
		        </ul>
		      </nav>

			<div class="section-body">
				<h1>Manage users</h1>


				<table id="dataTable" class="dummy-inventory">
					<thead>
						<tr>
							<th>User ID</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email Address</th>
							<th>Role</th>
							<th>Supervisor</th>
							<th>Supervisor<br/>Email Address</th>
							<th>Action</th>
						</tr>
					</thead>
				</table>

				<button name="add-user" class="button medium-button affirmative"
					onclick="dothis('add')">Add a user</button>
			</div>
		</section>
	</div>

	<footer> Prevent Child Abuse Tennessee </footer>

</body>
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
	            { "data": "supervisorEmail" },
	            { "render": function(data, type, row, meta) {
	            	return '<input type="hidden" name="userId"><a class="update_delete" onclick="submitRequest(' + row.id + ');">Update/Delete</a>';
	              }
	            }

	        ]
	    } );
	});
	function submitRequest(id)  {
	    var payload = {
	        "id": id
	    };
	    var $form = $('<form method="GET" action="getUser"></form>');
	    for (var key in payload) {
	        $('<input>').attr('type','hidden').attr('name',key).attr('value',payload[key]).appendTo($form);
	    }
	    var csrf = $("#_csrf_name");
	    console.log("csrf:  " + JSON.stringify(csrf));
	    csrf.appendTo($form);
	    $(document.body).append($form);
	    $form.submit();
	}

	function dothis(parameter) {
	 if(parameter == 'add') {
		 window.location.href="addUserPage";
	 }

	}

</script>
</html>
