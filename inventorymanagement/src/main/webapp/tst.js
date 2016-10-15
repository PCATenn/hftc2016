$(document).ready(function(){
    $('#myTable').DataTable( {
        "ajax": {
            "url": "inventorymanagement/testprod?",
            "dataSrc": ""
        },            
        "columns": [
            { "data": "productName" },
            { "data": "productDesc" }
        ]
    } );
});
