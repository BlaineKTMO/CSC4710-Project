<%@ page import="java.util.List" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NFT DISPLAY</title>
</head>
<body>
	<div align="center">
	<h1>View NFT</h1>
		<table border="1" cellpadding="6">
				<caption>
                    <h2>NFT INFORMATION</h2>
                </caption>
				<tr>
					<th>ID</th>
                    <th>Name</th>
                    <th>URL</th>
                    <th>Creator</th>
                    <th>Owner</th>
                    <th>Mint Time</th>
                    <th>Description</th>
                    <th>Price</th>
                </tr>
			<c:forEach var="nft" items="${nfts}">
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
			</c:forEach>
				<td>
					<c:out value="${price}"/>
				</td>
			</tr>
		</table>
	
	</div>
</body>
</html>