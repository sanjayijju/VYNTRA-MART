<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />

<link rel="stylesheet" href="CSS/style.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--<script type="text/javascript" src="JS/jquery.min.js"></script>-->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="JS/main.js"></script>

<!--csrf token code starts-->

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

<!--csrf token code ends-->

</head>


<div>
  <div class="row nav">
    <div class="nav_btn col"><a class="alink" href="/">Vyntra</a></div>
    <div class="nav_btn col" id="Types"><a class="alink" href="/">Types</a></div>
    <div class="nav_btn col" id="Brands"><a class="alink" href="/">Brands</a></div>
    <div class="nav_btn col-6" id="SearchContainer">
      <div class="search">
        <div class="bar">
          <div class="icon">
            <i></i>
          </div>
        </div>
        <form>
          <input type="text">
        </form>
      </div>
      <div class="close"></div>
    </div>
    <div class="nav_btn col"><a href="/login">Login</a></div>
  </div>
</div>

<div>
  <div class="row">
    <div class="col"></div>
    <div class="col-10" id="ProductTypesParentDiv"><div class="col-11 row" id="ProductTypes"></div></div>
  </div>
</div>

<div>
  <div class="row">
    <div class="col"></div>
    <div class="col-9" id="ProductBrandsParentDiv"><div class="col-11 row" id="ProductBrands"></div></div>
  </div>
</div>

</html>