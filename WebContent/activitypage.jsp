<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Activity page</title>
</head>
<center>
	<h1>Welcome! You have been successfully logged in</h1>
</center>
<body>
	<center>
		<a href="login.jsp" target="_self"> logout</a><br><br>
		<p> Select from one of the options below: </p>
	</center>
	<div align="center">
		<button onClick="window.location.href='mint.jsp';">
			Mint an NFT
		</button>
		<form action="createListing" method=get>
			<button type="submit">Create a Listing</button>
		</form>
<!-- 		<form action="viewListings" method=get> -->
<!-- 			<button type="submit">View Listings</button> -->
<!-- 		</form> -->
		<form action="transferNFT" method=get>
			<button type="submit">Transfer an NFT</button>
		</form>
		<button onClick="window.location.href='searchNFT.jsp';">
			Search for an NFT
		</button>
		<form action="creatorList" method="post">
			<button type="submit">ListNftsCreated</button>
		</form>
		<button onClick="window.location.href='idView.jsp';">
			Search Users
		</button>
		<form action="getBought" method=get>
			<button type="submit">Purchase History</button>
		</form>
		<form action="getSold" method=get>
			<button type="submit">Selling History</button>
		</form>
	</div>
</body>
</html>