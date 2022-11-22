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
	<h1>NFT</h1>
	
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
	
	</div>
</body>
</html>