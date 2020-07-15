<!DOCTYPE html>
<html>
<head>
<style>
.grid-container {
  display: grid;
  grid-gap: 5px;
  background-color: blue;
  padding: 5px;
}

.grid-item {
  background-color: white;
  text-align: center;
  padding: 20px;
  font-size: 30px;
}

.item1 {
  grid-column: 1 / span 3;
  grid-row: 1;
}

.item2 {
  grid-column: 3;
  grid-row: 2 / span 2;
}

.item5 {
  grid-column: 1 / span 3;
  grid-row: 3;
}
</style>
</head>

<%@ include file = "header.jsp" %>
<body>


<div class="grid-container">
  <div class="grid-item item1">1</div>
  <div class="grid-item item2">2</div>
  <div class="grid-item item3"><label for="name">Name:</label><input type="text" id="name" name="name" value = "${user.userName}" required></div>
  <div class="grid-item item4"><label for="number">Phone Number</label><input type="text" id="name" name="name" value = "${user.contactNumber}" required></div>
  <div class="grid-item item3"><label for="gender">Gender</label><input type="radio" id="male" name="gender" value="male">
  <label for="male">Male</label><br>
  <input type="radio" id="female" name="gender" value="female">
  <label for="female">Female</label><br>
  <input type="radio" id="other" name="gender" value="other">
  <label for="other">Other</label></div>
  <div class="grid-item item4"><label for="birthday">Date Of Birth:</label>
  <input type="date" id="birthday" name="birthday"></div>
</div>


</body>
<%@ include file = "footer.jsp" %>
</html>