<%@ page import="java.util.List" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Users</title>
</head>
<body>
<h1>Search for a User</h1>
	<form action="searchUser" method="post">
	<label for="user">Name: </label>
	<input type="text" size=45 name="user">
	<input type="submit" value="search">
	</form>
</body>
</html>