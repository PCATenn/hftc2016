<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="vendor/jquery-1.12.4.min.js"></script>
    <link rel="stylesheet" type="text/css" href="vendor/datatables.min.css"/>
 
    <script type="text/javascript" src="vendor/datatables.min.js"></script>

    <link rel="stylesheet" type="text/css" href="css/app.css" media="screen" />
    <title>PCAT inventory | Review Approvals</title>

<script type="text/javascript"> 

var url = "listAllInventoriesPending"
	$(document).ready(function(){
	    $('#dataTable').DataTable( {
	        "ajax": {
	            "url": url,
	            "dataSrc": ""
	        },
	        "columnDefs": [
	            { "targets": "_all", "className": "table-cell" },
	            { "targets": 0, "data": "id" },
	            { "targets": 1, "data": "familyId" },
	            { "targets": 2, "data": "quantity" },
	            { "targets": 3, "data": "status" },
	            { "targets": 4, "render":
	              function(data, type, row, meta) {
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
	    var $form = $('<form method="POST" action="requestApproval"></form>');
	    for (var key in payload) {
	        $('<input>').attr('type','hidden').attr('name',key).attr('value',payload[key]).appendTo($form);
	    }
	    $form.submit();
	}

</script>

</head>
<body>

    <nav>
        <a href="http://www.pcat.org/">
            <img src="img/PCA-Logo_TN_2C_sm.jpg" alt="PCAT logo" class="pcat-logo">
        </a>

     <button name="logout" class="button logout-button neutral">
            Log out
        </button>
    </nav>

     <section>
        <div class="section-body">
            <h1>
                Review Pending Approvals
            </h1>

            <form action="foo" id="request-items" style="padding: 0 5rem;">

                <input type="hidden" name="userId" value="1">

                  <h2 style="margin: -2rem 5rem 3rem 5rem">
                    Approve an Inventory by clicking on Approve button for an item:
                </h2>

                <div style="padding: 0 5rem;">
                    <table id="dataTable">
                        <thead>
                        <tr>
                            <th>Inventory Id</th>
                            <th>Family Name</th>
                            <th>Quantity</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                    </table>
                </div>

            </form>

        </div>
    </section>

</body>
</html>
