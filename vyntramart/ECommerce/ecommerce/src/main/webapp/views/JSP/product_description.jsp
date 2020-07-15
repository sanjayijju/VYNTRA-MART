<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>

.product_description_main{
    width: 90%;
    margin: 50px auto 0px auto;
}
.productdiv
{
     width:85%;
     height:600px;
     border:3px solid black;
     border-radius:12px;
     background-color:yelow;
     float:left;
}
.btndivi{
    width: 13%;
    float: right;
}
.leftproddiv
{
     float:left;
     width:48%;
     height:99.5%;
     background-color:blue;
     border-radius:10px;
     margin:1px;

}
.rightproddiv
{
     margin:1px;
     float:right;
     width:50%;
     height:99.5%;
     background-color:red;
     border-radius:10px;
}

</style>
</head>

<%@ include file = "header.jsp" %>

<body>
<div class="product_description_main container">
<div class="productdiv">
      <div class="leftproddiv">
	<p> ${product.image}</p>
      </div>
      <div class="rightproddiv">
	<p>Product Name: ${product.productName}</p><br>
	<p>Stock : ${product.stock}</p>
	<p>Product Price is: ${product.price}</p>
	<p>Product discount : ${product.discount}</p>
	<p>Product description: ${product.description}</p>
	<p>Product Type: ${product.productType.typeName}</p>
	<p>Product Brand: ${product.productBrand.brandName}</p>
      </div>
</div>
<div class="btndivi">
    <button type="button" class="btn btn-primary" id="add_to_wishlist">Add to wishlist</button><br><br>
    <button type="button" class="btn btn-primary" id="add_to_cart">Add to cart</button><br><br>
    <button type="button" class="btn btn-success" id="purchase">Purchase</button><br><br>
</div>
</div>
</body>
<div class="footer">
</div>
</html>