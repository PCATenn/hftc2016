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
            { "data": "availQty" }
        ]
    } );
});
