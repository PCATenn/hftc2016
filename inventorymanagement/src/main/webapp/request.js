var url = "listAllInventories"
$(document).ready(function(){
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
                  
                  return '<input type="text" name="quantity" id="qty'+row.id+'" />';
              }
            },
            { "targets": 5, "render":
              function(data, type, row, meta) {
                  return '<a onclick="submitRequest('+row.id+');">Request</a>';
              }
            }
        ]
    } );
});

function submitRequest(id)  {
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
