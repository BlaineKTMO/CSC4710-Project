<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Mint NFT</title>
	</head>
	<body>
		<center><h1>Mint a new NFT</h1></center>
		<div align="center">
			<form action="mint" method="post">
				<table>
					<tr>
						<th> Name </th>
						<td>
							<input type="text" name="name" size=45 autofocus>
						</td>
					</tr>
					<tr>
						<th> Image </th>
						<td>
							<input type="text" name="image" size=100>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="mint"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>