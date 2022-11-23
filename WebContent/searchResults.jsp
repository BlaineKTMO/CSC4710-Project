<%@ page import="java.util.List" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Results</title>
</head>
<body>
	<h1>Search Results</h1>
	
	<div align="center">
		<table>
			<c:forEach var="nft" items="${nfts}">
			<tr>
				<td>
					<c:out value="${nft.nftid}"/>
				<td>
				<td>
					<c:out value="${nft.name}"/>
				<td>
				<td>
					<c:out value="${nft.url}"/>
				<td>
				<td>
					<c:out value="${nft.creator}"/>
				<td>
				<td>
					<c:out value="${nft.owner}"/>
				<td>
				<td>
					<c:out value="${nft.mintTime}"/>
				<td>
			</tr>
			</c:forEach>
		</table>
	
		<label for="nft">NFT:</label>
		
	<form action="purchaseNFT" method="post">
	<select name="nft" id = "nft">
		<c:forEach var="nft" items="${nfts}">
		<option>
			<c:out value="${nft.name}"/>
		</option>
		</c:forEach>
	</select>
	<input type="submit" name="purchase" value="Purchase">
	</form>
		<form action="viewNFT" method="post">
		<select name="nft" id = "nft">
			<c:forEach var="nft" items="${nfts}">
			<option>
				<c:out value="${nft.nftid}"/>
			</option>
			</c:forEach>
		</select>
		<input type="submit" name="viewNFT" value="View">
		</form>
</body>
</html>