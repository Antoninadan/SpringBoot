<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Main Page</title>
</head>
<body>
<h1>Your login or password are wrong!!!
</h1>
<br>
<form action="authorization" method="post">
    <input type="text" size="30" name="login" required />
    <br>
    <input type="password" size="30" name="pass" required />
    <br>
    <input type="submit" value="LOGIN"/>
</form>
</body>
</html>
