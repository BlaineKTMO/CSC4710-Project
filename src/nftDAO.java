import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Servlet implementation class Connect
 */
@WebServlet("/nftDAO")
public class nftDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private userDAO userDAO = new userDAO();

	public nftDAO() {
	}

	private String getTime() {
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		String datetime = String.format("%d::%d::%d %d::%d::%d", date.getYear(), date.getMonthValue(),
				date.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond());

		return datetime;
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected void connect_func() throws SQLException {
		// uses default connection to the database
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=pass1234");
			System.out.println(connect);
		}
	}
	public boolean database_login(String email, String password) throws SQLException {
		try {
			connect_func("root", "pass1234");
			String sql = "select * from user where email = ?";
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			System.out.println("failed login");
			return false;
		}
	}

	// connect to the database
	public void connect_func(String username, String password) throws SQLException {
		if (connect == null || connect.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			connect = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/userdb?" + "useSSL=false&user=" + username + "&password=" + password);
			System.out.println(connect);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (connect != null && !connect.isClosed()) {
			connect.close();
		}
	}

	public List<NFT> viewNFT(String nftid) throws SQLException {
		List<NFT> resultNFTs = new ArrayList<NFT>();
		String sql = "SELECT * FROM NFTs WHERE nftid = '" + nftid + "'";
	
		connect_func();
	
		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);
	
		while (results.next()) {
			NFT nft = new NFT();
			nft.setNftid(results.getString("nftid"));
			nft.setName(results.getString("name"));
			nft.setUrl(results.getString("url"));
			nft.setCreator(results.getString("creator"));
			nft.setOwner(results.getString("owner"));
			nft.setMintTime(results.getString("mintTime"));
			nft.setDescription(results.getString("description"));
	
			resultNFTs.add(nft);
		}
	
		results.close();
		disconnect();
	
		return resultNFTs;
	}

	public List<NFT> mintedList(String nftCreator) throws SQLException {
		List<NFT> resultNFTs = new ArrayList<NFT>();
		String sql = "SELECT * FROM NFTs WHERE creator = '" + nftCreator + "'";
		connect_func();
	
		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);
	
		while (results.next()) {
			NFT nft = new NFT();
			nft.setNftid(results.getString("nftid"));
			nft.setName(results.getString("name"));
			nft.setUrl(results.getString("url"));
			nft.setCreator(results.getString("creator"));
			nft.setOwner(results.getString("owner"));
			nft.setMintTime(results.getString("mintTime"));
			nft.setDescription(results.getString("description"));
	
			resultNFTs.add(nft);
		}
	
		results.close();
		disconnect();
	
		return resultNFTs;
	}

	public List<NFT> ownedNftList(String nftOwner) throws SQLException {
		List<NFT> resultNFTs = new ArrayList<NFT>();
		String sql = "SELECT * FROM NFTs WHERE owner = '" + nftOwner + "'";
		connect_func();
	
		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);
	
		while (results.next()) {
			NFT nft = new NFT();
			nft.setNftid(results.getString("nftid"));
			nft.setName(results.getString("name"));
			nft.setUrl(results.getString("url"));
			nft.setCreator(results.getString("creator"));
			nft.setOwner(results.getString("owner"));
			nft.setMintTime(results.getString("mintTime"));
			nft.setDescription(results.getString("description"));
	
			resultNFTs.add(nft);
		}
	
		results.close();
		disconnect();
	
		return resultNFTs;
	}

	public List<String> getNFTNames() throws SQLException {
		String sql = "SELECT name FROM NFTs";
		List<String> names = new ArrayList<String>();
	
		connect_func();
	
		statement = connect.createStatement();
		ResultSet results = statement.executeQuery(sql);
	
		while (results.next()) {
			String nftName = results.getString("name");
			names.add(nftName);
		}
	
		results.close();
		disconnect();
	
		return names;
	}

	public boolean mintNFT(String name, String image, String current_user, String description) throws SQLException {
		String sql = "INSERT INTO NFTs(name, url, creator, owner, minttime, description) VALUES (?, ?, ?, ?, ?, ?);";
	
	    connect_func();
	    preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
	    preparedStatement.setString(1, name);
	    preparedStatement.setString(2, image);
	    preparedStatement.setString(3, current_user);
	    preparedStatement.setString(4, current_user);
	    preparedStatement.setString(5, getTime());
	    preparedStatement.setString(6, description);
	
	    preparedStatement.executeUpdate();
	    preparedStatement.close();
	
	    sql = String.format("SELECT nftid FROM NFTs where name=\"%s\"", name);
	    statement = (Statement) connect.createStatement();
	
	    ResultSet results = statement.executeQuery(sql);
	    results.next();
	
	    userDAO.insertTransaction(results.getInt("nftid"), 0, 0, current_user, current_user);
	
	    results.close();
	    disconnect();
	
	
	
	    return true;
	}

	public boolean transferNFT(String nftName, String targetUser, Double price) throws SQLException {
		// Need to add input validation
		String nftid = userDAO.getNFTID(nftName);
		String sql = "UPDATE NFTs SET owner = ? WHERE nftid = ?";
	
		connect_func();
		
		String trans = String.format("SELECT nftid, owner FROM NFTs where name=\"%s\"", nftName);
	    statement = (Statement) connect.createStatement();
	
	    ResultSet results = statement.executeQuery(trans);
	    results.next();
	
	    userDAO.insertTransaction(results.getInt("nftid"), price, 1, results.getString("owner"), targetUser);
	    
	    results.close();
		disconnect();
		
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, targetUser);
		preparedStatement.setString(2, nftid);
		preparedStatement.executeUpdate();
		disconnect();
		
		return true;
	}
}