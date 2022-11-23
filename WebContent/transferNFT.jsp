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
	<h1>Transfer an NFT</h1>
	<form action="submitTransfer" method="post" id="nft">
	<label for="nft">NFT: </label>
	<label for="user">Recipient: </label>

	<button type="submit">Submit Transfer</button>
	</form>
	
			<select name="nft" id="nft" form="nft">
		<c:forEach var="nftName" items="${names}">
			<option>
				<c:out value="${nftName}" />
			</option>
			</c:forEach>
		</select>
		<select name="user" id="user">
		<c:forEach var="usernames" items="${users}">
			<option>
				<c:out value="${usernames}" />
			</option>
			</c:forEach>
		</select>
</body>

</html>