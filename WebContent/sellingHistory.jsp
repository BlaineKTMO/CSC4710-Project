<%@ page import="java.util.List" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Selling History</title>
</head>
<body>
	<div align="center">
    <h1>Selling History</h1>
    <table border="1" cellpadding="6">
				<tr>
                    <th>Trans ID</th>
                    <th>Recipient</th>
                    <th>NFT Name</th>
                    <th>TimeStamp</th>
                    <th>Price</th>
                </tr>
        <c:forEach var="tran" items="${trans}">
        <tr>
            <td>
                <c:out value="${tran.transid}"/>
            </td>
            <td>
                <c:out value="${tran.recipient}"/>
            </td>
            <td>
                <c:out value="${tran.nftName}"/>
            </td>
            <td>
                <c:out value="${tran.timestamp}"/>
            </td>
            <td>
                <c:out value="${tran.price}"/>
            </td>
        </tr>
        </c:forEach>
    </table>
     <a href = "javascript:history.back()">Back to previous page</a>
    </div>
</body>
</html>