$(document).ready(function(){
    $('#dataTable').DataTable( {
        "ajax": {
            "url": "mockproduct.json",
            "dataSrc": ""
        },
        "columns": [            
            { "data": "productName" },
            { "data": "productDesc" },
            { "data": "location" },
            { "render":
              function(data, type, row, meta) {
                return row.totalInventory - row.reservedInventory;
              }
            },
            { "render": function(data, type, row, meta) {
                
                  return '<input type="hidden" name="itemid"><a href="/whatever?id=' + row.id + '">Request</a>';
              }
            }
        ]
    } );
});
