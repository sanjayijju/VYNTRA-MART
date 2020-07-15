<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript" src="JS/jquery.min.js"></script>
    <script type="text/javascript" src="JS/admin_dashboard.js"></script>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <script>
   $(function () {
     var token = $("meta[name='_csrf']").attr("content");
     var header = $("meta[name='_csrf_header']").attr("content");
     $(document).ajaxSend(function(e, xhr, options) {
       xhr.setRequestHeader(header, token);
     });
   });
    </script>

</head>

<body>
<input type="hidden" id="csrfToken" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <form>
            Product Type Header
            Product Type: <br>
            <input type="text" name="TypeName"><br>
            <button id="SubmitProductType" type="button">Add Product Type</button>
    </form><br><br>

    <form>
           Product Brand Header
            Product Brand: <br>
            <input type="text" name="BrandName"><br>
            <button id="SubmitProductBrand" type="button">Add Product Brand</button>
    </form><br><br>

    <form>
            Product Header
            Product Name: <br>
            <input id="ProductName" type="text" name="ProductName"><br>
            Stock: <br>
            <input id="Stock" type="number" name="Stock"><br>
            Rating: <br>
            <input id="Rating" type="number" name="Rating"><br>
            Price: <br>
            <input id="Price" type="number" name="Price"><br>
            Discount: <br>
            <input id="Discount" type="number" name="Discount"><br>
            Image: <br>
            <input id="Image" type="text" name="Image"><br>
            Product Type: <br>
            <input id="ProductType" type="text" name="ProductType"><br>
            Product Brand: <br>
            <input id="ProductBrand" type="text" name="ProductBrand"><br>
            Description: <br>
            <textarea placeholder="Example: It is a wonderful Product!!"></textarea><br>
            <button id="SubmitProduct" type="button">Add Product</button>
    </form>

</body>

</html>