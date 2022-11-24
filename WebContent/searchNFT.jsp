<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search for an NFT</title>
</head>
<body>
	<h1>Search for an NFT</h1>
	<form action="searchNFT" method="post">
	<label for="targetName">Name: </label>
	<input type="text" size=45 name="targetName">
	<input type="submit" value="search">
	</form>
	 <a href = "javascript:history.back()">Back to previous page</a>
</body>
</html>