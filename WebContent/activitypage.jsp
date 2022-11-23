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
		<p> You can show all the transactions or other attributes here like balance, name of the user and
			others.</p>
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

		<table border="1" cellpadding="6">
				<caption>
                    <h2>NFTS CREATED</h2>
                </caption>
				<tr>
					<th>ID</th>
                    <th>Name</th>
                    <th>URL</th>
                    <th>Creator</th>
                    <th>Owner</th>
                    <th>Mint Time</th>
                    <th>Description</th>
                </tr>
			<c:forEach var="nft" items="${mintList}">
			<tr>
				<td>
					<c:out value="${nft.nftid}"/>
				</td>
				<td>
					<c:out value="${nft.name}"/>
				</td>
				<td>
					<c:out value="${nft.url}"/>
				</td>
				<td>
					<c:out value="${nft.creator}"/>
				</td>
				<td>
					<c:out value="${nft.owner}"/>
				</td>
				<td>
					<c:out value="${nft.mintTime}"/>
				</td>
				<td>
					<c:out value="${nft.description}"/>
				</td>
			</tr>
			</c:forEach>
		</table>
		</form>
		<button onClick="window.location.href='idView.jsp';">
			Search Users
		</button>
	</div>
</body>
</html>