<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
.outdiv
{
     float:left;
     margin:50px 100px;
     width:50%;
     height:600px;
     border:3px solid black;
     border-radius:12px;
     background-color:yelow;
}
.buttonsdiv
{
    margin:50px 0px 0px 0px;
    float:right;
     width:30%;
     position=absolute;
	 height=300px;
     border-radius:10px;
}

.leftdiv
{
     margin-top:20px;
     float:left;
     width:48%;
     height:95%;
     background-color:blue;
     border-radius:10px;
     margin:1px;

}
.rightdiv
{
     margin-top:20px;
     float:right;
     width:48%;
     height:35%;
	 background-color:red;
     border-radius:10px;
}
.container
{
    margin:80px 80px 10px 10px;
    float:left;
    width:30%;
    position=absolute;
	height=100px;
    border-radius:10px;
}


</style>
</head>

<%@ include file = "header.jsp" %>

<body>
<div class=outdiv>
      <div class=leftdiv>
	<center>hello</center>
      </div>
      <div class=rightdiv>
	<p>hii</p>
      </div>

</div>
<div class=buttonsdiv>


	  <button type="button" class="btn btn-success">Add To WishList</button>
	  <button type="button" class="btn btn-success">Continue Shopping</button>
	  </div>

</div>
 <div class="container">
  <button type="button" class="btn btn-success">purchase</button>
</div>


</body>
<div class="footer">
</div>
</html>