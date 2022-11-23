<%@ page import="java.util.List" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nfts Created</title>
</head>
<body>
 <div align="center">
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
	</div>
</body>
</html>