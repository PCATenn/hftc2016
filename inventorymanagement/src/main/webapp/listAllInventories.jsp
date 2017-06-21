<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
<title>Manage items | PCAT Inventory Management</title>

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
		          <li id="manage-users">  <a href="listAllUsers.jsp">         manage users        </a>    </li>
		        </sec:authorize>
		        </ul>
		      </nav>

				<div class="section-body">
					<h1>Manage items</h1>


					<table id="dataTable" class="dummy-inventory">
						<thead>
							<tr>
								<th >Inventory ID</th>
								<th >Item</th>
								<th >Description</th>
								<th >Location</th>
								<th >Quantity</th>
								<th >Action</th>
							</tr>
						</thead>
					</table>

					<button name="add-item" style="margin-top: 3rem;" class="button medium-button affirmative">
						Add an item
					</button>

				</div>
			</section>
			<input type="hidden"  id="_csrf_name" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</div>
	<footer>
		Prevent Child Abuse Tennessee
	</footer>

</body>
<script type="text/javascript">
	var url = "listAllItems";

	$(document).ready(function(){
	    $('#dataTable').DataTable( {
	        "ajax": {
	            "url": url,
	            "dataSrc": ""
	        },
	        "columns": [
				{ "data": "id" },
	            { "data": "productName" },
	            { "data": "productDesc" },
	            { "data": "location" },
	            { "data": "totalInventory" },
	            { "render": function(data, type, row, meta) {
	                  return '<input type="hidden" name="inventoryId"><a class="update_delete" onclick="submitRequest(' + row.id + ');">Update/Delete</a>';
	              }
	            }

	        ]
	    } );
	});
	function submitRequest(id)  {
	    var payload = {
	        "id": id
	    };
	    var $form = $('<form method="GET" action="getInventoryItem"></form>');
	    for (var key in payload) {
	        $('<input>').attr('type','hidden').attr('name',key).attr('value',payload[key]).appendTo($form);
	    }
	    var csrf = $("#_csrf_name");
	    console.log("csrf:  " + JSON.stringify(csrf));
	    csrf.appendTo($form);
	    $(document.body).append($form);
	    $form.submit();
	}

</script>
</html>
