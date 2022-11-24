<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Create Listing</title>
</head>

<body>
	<center><h1>Create a Listing</h1></center>
	<div align=center>
		<form action="submitListing" method="post" id="nft">
			<label for="nft">NFT: </label>
			<select name="nft" id="name" form="nft">
					<c:forEach var="nftName" items="${names}">
							<option>
							<c:out value="${nftName}" />
							</option>
					</c:forEach>
			</select>
						
			Price <input type="text" name="price" size=45>
			Days Available <input type="text" name="daysAvailable" size=45>
			<button type="submit">Submit Listing</button>

		</form>
		 <a href = "javascript:history.back()">Back to previous page</a>
	</div>
</body>

</html>