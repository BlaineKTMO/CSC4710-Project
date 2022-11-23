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
		<form name="submitListing" method="post">
			<label for="nft">NFT: </label>
			<c:forEach var="nftName" items="${names}">
				<select name="nft" id="nft">
					<option>
						<c:out value="${nftName}" />
					</option>
				</select>
			</c:forEach>
			
			Price <input type="text" name="price" size=45>
			Days Available <input type="text" name="daysAvailable" size=45>

		</form>
	</div>
</body>

</html>