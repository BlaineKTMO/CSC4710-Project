<%@ page import="java.util.List" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Show User</title>
</head>
<body>
	<h1>User Info</h1>
	<h2> Name </h2>
	<c:out value="${user.email}"/>
	<table border="1" cellpadding="6">
	            <caption>
                    <h2>NFTS OWNED</h2>
                </caption>
				<tr>
                    <th>NFT Name</th>
                    <th>NFT URL</th>
                    <th>Mint Time</th>
                </tr>
			<c:forEach var="nft" items="${nfts}">
			<tr>
				<td>
					<c:out value="${nft.name}"/>
				</td>
				<td>
					<c:out value="${nft.url}"/>
				</td>
				<td>
					<c:out value="${nft.mintTime}"/>
				</td>
			</tr>
			</c:forEach>
		</table>
</body>
</html>