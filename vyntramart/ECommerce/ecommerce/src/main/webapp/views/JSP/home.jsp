<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>Vyntra</title>

</head>

<%@ include file = "header.jsp" %>

<body>

  <div id="Advertisements">
    <i>advertisements</i>
  </div>

  <div id="TrendingProducts">
    <c:forEach items="${top_products}" var="product">
    <a class="alink" href="product_description/${product.productId}">

      <div class="ProductBox">

        <div class="ProductDiscount">
          ${product.discount} %  off
        </div>

        <div class="ProductImage">
          <!--<img src="lips.jpg" width="20%" height="350px" style="border-radius:15px;">-->
        </div>

        <div class="ProductDetails">
          <p>${product.productName}</p>
          <p>Price : Rs. ${product.price}</p>
        </div>
      </div>
    </a>
    </c:forEach>
  </div>

</body>

<%@ include file = "footer.jsp" %>
</html>