<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
<title>Manage items | PCAT inventory</title>

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
	            { "data": "productName" },
	            { "data": "productDesc" },
	            { "data": "totalInventory" },
	            { "data": "location" },
	            { "render": function(data, type, row, meta) {
	                
	                  return '<input type="hidden" name="inventoryId"><a href="gotoComplete?id=' + row.id + '">Update/Delete</a>';
	              }
	            }

	        ]
	    } );
	});
	
</script>
</head>
<body>

	<div class="content">
		<form action="gotoComplete" method="get">
				<input type="hidden" name="id" id="id" value="3"/>
				<input type="hidden" name="productName" id="productName" value="Car Seat"/>
				<input type="hidden" name="productDesc" id="productDesc" value="Infant"/>
				<input type="hidden" name="location" id="location" value="Nashville"/>
				<input type="hidden" name="totalInventory" id="totalInventory" value="3"/>
				<input type="hidden" name="userId" id="userId" value="${user.id}"/>
			<nav>
				<a href="http://www.pcat.org/"> <img
					src="img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo">
				</a>

				<button name="logout" class="button logout-button neutral">
					Log out</button>
			</nav>

			<section>
				<div class="section-body">
					<h1>Manage inventory</h1>


					<table id="dataTable" class="dummy-inventory">
						<thead>
							<tr>
								<th >Inventory Id</th>
								<th >Product Name</th>
								<th >Product Description</th>
								<th >Quantity</th>
								<th >Location</th>
								<th ></th>
							</tr>
						</thead>
					</table>

					<button name="continue-request" class="button medium-button affirmative">
						Add an item
					</button>

				</div>
			</section>
		</form>
	</div>

	<footer>
		Prevent Child Abuse Tennessee
	</footer>

</body>
</html>
