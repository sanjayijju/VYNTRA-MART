<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form autocomplete="off" action="registration" method="post">
	<br>
	<h3>Your details</h3>
<div>
	User Name:
	<input type="text" name="username" required><br><br>
</div>
<div>
	Password:
	<input type="Password" name="password" required><br><br>
</div>
<div>
	ConfirmPassword:
	<input type="Password" name="confirmPassword"required><br><br>
</div>
<div>
	Email:
	<input type="Email" name="email" placeholder="example@domain.com" autofocus><br><br>
</div>
<div>
	Phone:
	<input type="tel" name="phone" required><br><br>
</div>
<br>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" name="submit" value="register" required><br><br>

	<br><br>
</form>


</body>


</html>