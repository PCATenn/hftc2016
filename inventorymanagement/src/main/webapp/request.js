var url = "listAllInventories"
$(document).ready(function(){
	var request_items_form = $("#request-items");
	jQuery.validator.setDefaults({
		  debug: true,
		  success: "valid"
		});
	/*
	jQuery.validator.addMethod("familyNumber", function (value, element){
			var familyNumber = $("#familyNumber").val();
			var regex = /^\w{4}.*\d+/;
			return regex.test(familyNumber);
	});
	*/
	$("#request-items").validate({
		rules: {
			"familyNumber": {
				required: true,
				minlength: 5,
				pattern: /^\w{4}.*\d+/
			},
			"quantity": {
				required: true,
				min: 1
			}
		},
		messages: {
			"familyNumber": "Please enter a 4 character county followed by at least one number",
			"quantity": "Quantity must be entered"
		}
		
	});
    $('#dataTable').DataTable( {
        "ajax": {
            "url": url,
            "dataSrc": ""
        },
        "columnDefs": [
            { "targets": "_all", "className": "table-cell" },
            { "targets": 0, "data": "productName" },
            { "targets": 1, "data": "productDesc" },
            { "targets": 2, "data": "location" },
            { "targets": 3, "render":
              function(data, type, row, meta) {
                return row.totalInventory - row.reservedInventory;
              }
            },
            { "targets": 4, "render":
              function(data, type, row, meta) {
                  
                  return '<input  type="text" value="1" name="quantity" id="qty'+row.id+'" />';
              }
            },
            { "targets": 5, "render":
              function(data, type, row, meta) {
                  return '<a class="requestApproval" onclick="submitRequest('+row.id+');">Request</a>';
              }
            }
        ]
    } );

});

function submitRequest(id)  {
	if(!$("#request-items").valid()){
		alert("Please check the family number and the quantity");
		return;
	}
    var qty = $('#qty'+id).val();
    var familyId = $('#request-items .family-number-input').val();
    userId = $('#request-items input[name=userId]').val();
    var payload = {
        "userId": userId,
        "familyId": familyId,
        "quantity": qty,
        "inventoryId": id
    };
    var $form = $('<form method="POST" action="submitForRequestApproval"></form>');
    for (var key in payload) {
        $('<input>').attr('type','hidden').attr('name',key).attr('value',payload[key]).appendTo($form);
    }
    $form.submit();
}

