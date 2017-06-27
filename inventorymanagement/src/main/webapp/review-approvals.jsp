<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/png" href="img/PCA-Logo_TN_2C_sm_ico.png">
    <script src="vendor/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="vendor/datatables.min.css"/>

    <script type="text/javascript" src="vendor/datatables.min.js"></script>

    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>Review approvals | PCAT Inventory Management</title>

<script type="text/javascript">

var url = "listPendingRequests"
	$(document).ready(function(){
	    $('#dataTable').DataTable( {
	        "ajax": {
	            "url": url,
	            "dataSrc": ""
	        },
	        "columnDefs": [
	            { "targets": "_all", "className": "table-cell" },
	            { "targets": 0, "data": "requestor"},
	            { "targets": 1, "data": "familyId" },
	            { "targets": 2, "data": "productName" },
	            { "targets": 3, "data": "location" },
	            { "targets": 4, "data": "quantity" },
	            { "targets": 5, "data": "status" },
	            { "targets": 6, "data": "totalInventory"},
	            { "targets": 7, "data": "reservedInventory"},
	            { "targets": 8, "data": "availableInventory"},
	            { "targets": 9, "render":
	              function(data, type, row, meta) {
	            	  console.log(row);
	            	  console.log(row.id);
	            	  console.log(row.familyInventory.id);
	                  return '<a onclick="approveRequest('+row.id+');">Approve</a>';
	              }
	            }
	        ]
	    } );
	});

	function approveRequest(id)  {
	    userId = $('#request-items input[name=userId]').val();
	    var payload = {
	        "userId": userId,
	        "familyInventoryId": id
	   };
	    var $form = $('<form method="POST" action="request/supervisorApproved"><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></form>');
	    for (var key in payload) {
	        $('<input>').attr('type','hidden').attr('name',key).attr('value',payload[key]).appendTo($form);
	    }
	    $(document.body).append($form)
	    $form.submit();
	}

</script>

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
	          <li class="current-link">                    <a href="review-approvals.jsp">     review approvals    </a>    </li>
	        </sec:authorize>
	        <sec:authorize access="hasRole('ADMINISTRATOR')">
	          <li id="manage-items">  <a href="listAllInventories.jsp">   manage items        </a>    </li>
	          <li id="manage-users">  <a href="listAllUsers.jsp">         manage users        </a>    </li>
	        </sec:authorize>
	        </ul>
	      </nav>

            <div class="section-body">
                <h1>
                    Review pending approvals
                </h1>

                <form action="foo" id="request-items" style="padding: 0 5rem;">

                    <input type="hidden" name="userId" value="${user.id}">

                    <h2 style="margin: -2rem 5rem 3rem 5rem">
                        Approve a request by clicking the Approve button for an item:
                    </h2>

                    <div style="padding: 0 5rem;">
                        <table id="dataTable">
                            <thead>
                                <tr>
                                    <th>Requester</th>
                                    <th>Family</th>
                                    <th>Item</th>
                                    <th>Location</th>
                                    <th>Requested<br/>Quantity</th>
                                    <th>Request<br/>Status</th>
                                    <th title="Total quantity of this item in our inventory">Total<br/>Inventory<br/>Quantity</th>
                                    <th title="Total quantity of this item in all pending approvals">Total<br/>Pending<br/>Approvals<br/>Quantity</th>
                                    <th title="Total Inventory Quantity minus Total Pending Approvals Quantity">Available<br/>Quantity</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                        </table>
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
