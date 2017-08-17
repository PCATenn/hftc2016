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

	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.4.0/css/buttons.dataTables.min.css">
	
	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js">
	</script>
	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.4.0/js/dataTables.buttons.min.js">
	</script>
	<script type="text/javascript" language="javascript" src="//cdn.datatables.net/buttons/1.4.0/js/buttons.flash.min.js">
	</script>
	<script type="text/javascript" language="javascript" src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js">
	</script>
	<script type="text/javascript" language="javascript" src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/pdfmake.min.js">
	</script>
	<script type="text/javascript" language="javascript" src="//cdn.rawgit.com/bpampuch/pdfmake/0.1.27/build/vfs_fonts.js">
	</script>
	<script type="text/javascript" language="javascript" src="//cdn.datatables.net/buttons/1.4.0/js/buttons.html5.min.js">
	</script>
	<script type="text/javascript" language="javascript" src="//cdn.datatables.net/buttons/1.4.0/js/buttons.print.min.js">
	</script>

    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>All Requests | PCAT Inventory Management</title>

<script type="text/javascript">

	var url = "listAllRequests";
	$(document).ready(function(){
	    $('#dataTable').DataTable( {
	        "ajax": {
	            "url": url,
	            "dataSrc": ""
	        },
	        dom: 'Bfrtip',
			buttons: [
				'copy', 'csv', 'excel', 'pdf', 'print'
			],
	        "columnDefs": [
	            { "targets": "_all", "className": "table-cell" },
	            { "targets": 0, "data": "requestor"},
	            { "targets": 1, "data": "familyId" },
	            { "targets": 2, "data": "productName" },
	            { "targets": 3, "data": "location" },
	            { "targets": 4, "data": "quantity" },
	            { "targets": 5, "data": "status" },
	        ]
	    } );
	});

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
			<jsp:include page="menu.jsp"></jsp:include>
            <div class="section-body">
                <h1>
                    all requests 
                </h1>

                <form action="foo" id="request-items" style="padding: 0 5rem;">

                    <input type="hidden" name="userId" value="${user.id}">

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
