<%@ page import="java.util.List" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer NFT</title>
</head>

<body>
	<div align="center">
	<h1>Transfer an NFT</h1>
	<form action="submitTransfer" method="post" id="nft">
	<label for="nft">NFT: </label>
	<select name="nft" id="nft" form="nft">
		<c:forEach var="nftName" items="${names}">
			<option>
				<c:out value="${nftName}" />
			</option>
			</c:forEach>
		</select>
		<label for="user">Recipient: </label>
		<select name="user" id="user">
		<c:forEach var="usernames" items="${users}">
			<option>
				<c:out value="${usernames}" />
			</option>
			</c:forEach>
		</select>
	<button type="submit">Submit Transfer</button>
	</form>
		 <a href = "javascript:history.back()">Back to previous page</a>
		 </div>
</body>
</html>