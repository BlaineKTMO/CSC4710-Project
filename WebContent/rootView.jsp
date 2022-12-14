<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Root page</title>
</head>

<body>

    <div align="center">

        <form action="initialize">
            <input type="submit" value="Initialize the Database" />
        </form>
        <a href="login.jsp" target="_self"> logout</a><br><br>

        <h1>List all users</h1>
        <div align="center">
            <table border="1" cellpadding="6">
                <caption>
                    <h2>List of Users</h2>
                </caption>
                <tr>
                    <th>Email</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Address</th>
                    <th>Password</th>
                    <th>Birthday</th>
                </tr>
                <c:forEach var="users" items="${listUser}">
                    <tr style="text-align:center">
                        <td>
                            <c:out value="${users.email}" />
                        </td>
                        <td>
                            <c:out value="${users.firstName}" />
                        </td>
                        <td>
                            <c:out value="${users.lastName}" />
                        </td>
                        <td>
                            <c:out
                                value="${users.adress_street_num} ${users.adress_street} ${users.adress_city} ${users.adress_state} ${users.adress_zip_code}" />
                        </td>
                        <td>
                            <c:out value="${users.password}" />
                        </td>
                        <td>
                            <c:out value="${users.birthday}" />
                        </td>
                </c:forEach>
            </table>
        </div>
        
        <table border="1" cellpadding="6">
        <caption>
        	<h2>Big Creators</h2>
        </caption>
                <tr>
                    <th>User(s)</th>
                    <th>Amount Created</th>
                </tr>
         <c:forEach var="mostCreated" items="${mostCreated}">
         <tr style="text-align:center">
            <td>
           		<c:out value = "${mostCreated.name}"/>
       		</td>
       		<td>
       			<c:out value = "${mostCreated.nftid}"/>
       		</td>
       	</c:forEach>
       	</table>
       	
       	<table border="1" cellpadding="6">
        <caption>
        	<h2>Big Sellers</h2>
        </caption>
                <tr>
                    <th>User(s)</th>
                    <th>Amount Sold</th>
                </tr>
         <c:forEach var="mostSold" items="${mostSold}">
         <tr style="text-align:center">
            <td>
           		<c:out value = "${mostSold.name}"/>
       		</td>
       		<td>
       			<c:out value = "${mostSold.nftid}"/>
       		</td>
       	</c:forEach>
       	</table>
       	
       	<table border="1" cellpadding="6">
        <caption>
        	<h2>Big Buyer</h2>
        </caption>
                <tr>
                    <th>User(s)</th>
                    <th>Amount Bought</th>
                </tr>
         <c:forEach var="mostBought" items="${mostBought}">
         <tr style="text-align:center">
            <td>
           		<c:out value = "${mostBought.name}"/>
       		</td>
       		<td>
       			<c:out value = "${mostBought.nftid}"/>
       		</td>
       	</c:forEach>
       	</table>
       	
       	<table border="1" cellpadding="6">
        <caption>
        	<h2>Hot NFT's</h2>
        </caption>
                <tr>
                    <th>Nft ID</th>
                    <th>Amount of Owners</th>
                </tr>
         <c:forEach var="mostOwners" items="${mostOwners}">
         <tr style="text-align:center">
            <td>
           		<c:out value = "${mostOwners.name}"/>
       		</td>
       		<td>
       			<c:out value = "${mostOwners.nftid}"/>
       		</td>
       	</c:forEach>
       	</table>
       	
       	<br>
       	<table border="1" cellpadding="6">
        <caption>
        	<h2>Common NFTs</h2>
        </caption>
                <tr>
                    <th>NFT ID</th>
                </tr>
         <c:forEach var="commonNFT" items="${commonNFT}">
         <tr style="text-align:center">
            <td>
           		<c:out value = "${commonNFT.name}"/>
       		</td>
       	</c:forEach>
       	</table>
       	<form action="root" method="post">
		<select name="user1" id = "user1">
		<c:forEach var="user" items="${user}">
		<option>
			<c:out value="${user.email}"/>
		</option>
		</c:forEach>
		</select>
		<select name="user2" id = "user2">
		<c:forEach var="user" items="${user}">
		<option>
			<c:out value="${user.email}"/>
		</option>
		</c:forEach>
		</select>
		<input type="submit" value = "rootSearch">
		</form>
		
       	<table border="1" cellpadding="6">
        <caption>
        	<h2>Diamond Hands</h2>
        </caption>
                <tr>
                    <th>Name</th>
                </tr>
         <c:forEach var="diamondHands" items="${diamondHands}">
         <tr style="text-align:center">
            <td>
           		<c:out value = "${diamondHands.name}"/>
       		</td>
       	</c:forEach>
       	</table>
       	
       	<table border="1" cellpadding="6">
        <caption>
        	<h2>Paper Hands</h2>
        </caption>
                <tr>
                    <th>Name</th>
                </tr>
         <c:forEach var="paperHand" items="${paperHands}">
         <tr style="text-align:center">
            <td>
           		<c:out value = "${paperHand.email}"/>
       		</td>
       	</c:forEach>
       	</table>
       	
       	<table border="1" cellpadding="6">
        <caption>
        	<h2>Good Buyer's</h2>
        </caption>
                <tr>
                    <th>Name</th>
                </tr>
         <c:forEach var="goodBuyer" items="${goodBuyer}">
         <tr style="text-align:center">
            <td>
           		<c:out value = "${goodBuyer.name}"/>
       		</td>
       	</c:forEach>
       	</table>
       	
       	<table border="1" cellpadding="6">
        <caption>
        	<h2>Inactive Users</h2>
        </caption>
                <tr>
                    <th>Name</th>
                </tr>
         <c:forEach var="inactiveUser" items="${inactiveUser}">
         <tr style="text-align:center">
            <td>
           		<c:out value = "${inactiveUser.name}"/>
       		</td>
       	</c:forEach>
       	</table>
       	
       	<table border="1" cellpadding="6">
        <caption>
        	<h2>Statistics</h2>
        </caption>
                <tr>
                    <th>Name</th>
                    <th>NFTs Owned</th>
                    <th>Buys</th>
                    <th>Sells</th>
                    <th>Transfers</th>
                </tr>
                
         <c:forEach var="Statistics" items="${Statistics}">
         <tr style="text-align:center">
            <td>
           		<c:out value = "${Statistics.name}"/>
       		</td>
       		<td>
           		<c:out value = "${Statistics.nftid}"/>
       		</td>
       		<td>
           		<c:out value = "${Statistics.owner}"/>
       		</td>
       		<td>
           		<c:out value = "${Statistics.creator}"/>
       		</td>
       		<td>
           		<c:out value = "${Statistics.url}"/>
       		</td>
       	</c:forEach>
       	</table>
       	
       	
       	
    </div>

</body>

</html>