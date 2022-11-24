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
@WebServlet("/userDAO")
public class userDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public userDAO() {
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

	public String getNFTID(String name) throws SQLException {
		String sql = "SELECT nftid FROM NFTs WHERE name = ?";
		String nftid = "0";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, name);

		ResultSet results = preparedStatement.executeQuery();

		if (results.next())
			nftid = results.getString("nftid");

		results.close();
		disconnect();

		return nftid;
	}
	
	public String getPrice(String nftid) throws SQLException {
		String sql = "SELECT price FROM listings WHERE nftid = ?";
		String price = "0";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, nftid);

		ResultSet results = preparedStatement.executeQuery();

		if (results.next())
			price = results.getString("price");

		results.close();
		disconnect();

		return price;
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

	public List<user> listAllUsers() throws SQLException {
		List<user> listUser = new ArrayList<user>();
		String sql = "SELECT * FROM User";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String email = resultSet.getString("email");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String password = resultSet.getString("password");
			String birthday = resultSet.getString("birthday");
			double balance = resultSet.getDouble("balance");
			String adress_street_num = resultSet.getString("adress_street_num");
			String adress_street = resultSet.getString("adress_street");
			String adress_city = resultSet.getString("adress_city");
			String adress_state = resultSet.getString("adress_state");
			String adress_zip_code = resultSet.getString("adress_zip_code");

			user users = new user(email, firstName, lastName, password, birthday, balance, adress_street_num, adress_street,
					adress_city, adress_state, adress_zip_code);
			listUser.add(users);
		}
		resultSet.close();
		disconnect();
		return listUser;
	}

	public List<Listing> viewListings() throws SQLException {
		List<Listing> listings = new ArrayList<Listing>();
		String sql = "SELECT * FROM Listings";

		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			Listing listing = new Listing();

			listing.listid = results.getString("listid");
			listing.owner = results.getString("owner");
			listing.nftid = results.getString("nftid");
			listing.startTime = results.getString("start");
			listing.endTime = results.getString("end");
			listing.price = results.getString("price");

			listings.add(listing);
		}

		results.close();
		disconnect();

		return listings;
	}

	public List<String> getUsernames() throws SQLException {
		List<String> usernames = new ArrayList<String>();
		String sql = "SELECT email FROM User";

		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			usernames.add(results.getString("email"));
		}

		results.close();
		disconnect();

		return usernames;
	}

	protected void disconnect() throws SQLException {
		if (connect != null && !connect.isClosed()) {
			connect.close();
		}
	}

	public void insert(user users) throws SQLException {
		connect_func("root", "pass1234");
		String sql = "insert into User(email, firstName, lastName, password, birthday,adress_street_num, adress_street,adress_city,adress_state,adress_zip_code) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getAdress_street_num());
		preparedStatement.setString(7, users.getAdress_street());
		preparedStatement.setString(8, users.getAdress_city());
		preparedStatement.setString(9, users.getAdress_state());
		preparedStatement.setString(10, users.getAdress_zip_code());

		preparedStatement.executeUpdate();
		preparedStatement.close();
	}

	public boolean delete(String email) throws SQLException {
		String sql = "DELETE FROM User WHERE email = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);

		boolean rowDeleted = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowDeleted;
	}

	public boolean update(user users) throws SQLException {
		String sql = "update User set firstName=?, lastName =?,password = ?,birthday=?,adress_street_num =?, adress_street=?,adress_city=?,adress_state=?,adress_zip_code=?, cash_bal=?, PPS_bal =? where email = ?";
		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getAdress_street_num());
		preparedStatement.setString(7, users.getAdress_street());
		preparedStatement.setString(8, users.getAdress_city());
		preparedStatement.setString(9, users.getAdress_state());
		preparedStatement.setString(10, users.getAdress_zip_code());

		boolean rowUpdated = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		return rowUpdated;
	}

	public user getUser(String email) throws SQLException {
		user user = null;
		String sql = "SELECT * FROM User WHERE email = ?";

		connect_func();

		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);

		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String password = resultSet.getString("password");
			String birthday = resultSet.getString("birthday");
			double balance = resultSet.getDouble("balance");
			String adress_street_num = resultSet.getString("adress_street_num");
			String adress_street = resultSet.getString("adress_street");
			String adress_city = resultSet.getString("adress_city");
			String adress_state = resultSet.getString("adress_state");
			String adress_zip_code = resultSet.getString("adress_zip_code");
			user = new user(email, firstName, lastName, password, birthday, balance, adress_street_num, adress_street,
					adress_city, adress_state, adress_zip_code);
		}

		resultSet.close();
		statement.close();

		return user;
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

	public boolean checkEmail(String email) throws SQLException {
		boolean checks = false;
		String sql = "SELECT * FROM User WHERE email = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, email);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println(checks);

		if (resultSet.next()) {
			checks = true;
		}

		System.out.println(checks);
		return checks;
	}

	public boolean checkPassword(String password) throws SQLException {
		boolean checks = false;
		String sql = "SELECT * FROM User WHERE password = ?";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, password);
		ResultSet resultSet = preparedStatement.executeQuery();

		System.out.println(checks);

		if (resultSet.next()) {
			checks = true;
		}

		System.out.println(checks);

		resultSet.close();
		disconnect();
		return checks;
	}

	public boolean isValid(String email, String password) throws SQLException {
		String sql = "SELECT * FROM User";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		resultSet.last();

		int setSize = resultSet.getRow();
		resultSet.beforeFirst();

		for (int i = 0; i < setSize; i++) {
			resultSet.next();
			if (resultSet.getString("email").equals(email) && resultSet.getString("password").equals(password)) {
				return true;
			}
		}

		disconnect();

		return false;
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

        this.insertTransaction(results.getInt("nftid"), 0, 0, current_user, current_user);

        results.close();
        disconnect();



        return true;
	}

	public boolean submitListing(String nftName, String user, String daysAvailable, String price) throws SQLException {
		String sql = "INSERT INTO Listings(owner, nftid, start, end, price) VALUES (?, ?, ?, ?, ?)";
		String getId = String.format("SELECT nftid FROM NFTs WHERE name=\"%s\"", nftName);
		LocalDate startDate = LocalDate.now();
		LocalTime time = LocalTime.now();
		LocalDate endDate = startDate.plusDays(Integer.parseInt(daysAvailable));

		connect_func();

		statement = (Statement) connect.createStatement();

		ResultSet results = statement.executeQuery(getId);
		results.next();
		
		preparedStatement = connect.prepareStatement(sql);
		preparedStatement.setString(1, user);
		preparedStatement.setInt(2, results.getInt("nftid"));
		preparedStatement.setString(3, startDate + " " + time);
		preparedStatement.setString(4, endDate + " " + time);
		preparedStatement.setString(5, price);
		
		preparedStatement.executeUpdate();
		
		results.close();
		disconnect();

		return true;
	}

	public boolean transferNFT(String nftName, String targetUser, Double price) throws SQLException {
		// Need to add input validation
		String nftid = getNFTID(nftName);
		String sql = "UPDATE NFTs SET owner = ? WHERE nftid = ?";

		connect_func();
		
		String trans = String.format("SELECT nftid, owner FROM NFTs where name=\"%s\"", nftName);
        statement = (Statement) connect.createStatement();

        ResultSet results = statement.executeQuery(trans);
        results.next();

        this.insertTransaction(results.getInt("nftid"), price, 1, results.getString("owner"), targetUser);
        
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
	
	public boolean changeBalance(String user, double change) throws SQLException {
		// Need to add input validation
		String sql = "UPDATE user SET balance = ? WHERE email = ?";
		String getBal = String.format("SELECT balance FROM user WHERE email = \"%s\"", user);
		double balance;

		connect_func();
		
        statement = (Statement) connect.createStatement();

        ResultSet results = statement.executeQuery(getBal);
        results.next();

        balance = results.getDouble("balance");
        
        System.out.println("you balance is: " + balance);
        
        results.close();
		disconnect();
		
		double total = balance + change;
		System.out.println("change is" + change);
		System.out.println("cahange and balance"+ total);
		
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setDouble(1, balance + change);
		preparedStatement.setString(2, user);
		preparedStatement.executeUpdate();
		disconnect();
		
		return true;
	}

	public List<NFT> searchNFT(String nftName) throws SQLException {
		List<NFT> resultNFTs = new ArrayList<NFT>();
		String sql = "SELECT * FROM NFTs WHERE name LIKE '" + nftName + "%'";

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

	public List<user> searchUser(String user) throws SQLException {
		List<user> listUser = new ArrayList<user>();
		String sql = "SELECT * FROM user WHERE email = '" + user + "'";
		connect_func();
		statement = (Statement) connect.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			String email = resultSet.getString("email");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String password = resultSet.getString("password");
			String birthday = resultSet.getString("birthday");
			double balance = resultSet.getDouble("balance");
			String adress_street_num = resultSet.getString("adress_street_num");
			String adress_street = resultSet.getString("adress_street");
			String adress_city = resultSet.getString("adress_city");
			String adress_state = resultSet.getString("adress_state");
			String adress_zip_code = resultSet.getString("adress_zip_code");

			user users = new user(email, firstName, lastName, password, birthday, balance, adress_street_num, adress_street,
					adress_city, adress_state, adress_zip_code);
			listUser.add(users);
		}
		resultSet.close();
		disconnect();
		return listUser;
	}
	
	public boolean insertTransaction(int nftid, double price, int transType, String origin, String recipient) throws SQLException {
        String sql = "INSERT INTO Transactions(origin, recipient, nftid, transType, timestamp, price) VALUES (?, ?, ?, ?, ?, ?);";

        connect_func();
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, origin);
        preparedStatement.setString(2, recipient);
        preparedStatement.setInt(3, nftid);
        preparedStatement.setInt(4, transType);
        preparedStatement.setString(5, getTime());
        preparedStatement.setDouble(6, price);

        preparedStatement.executeUpdate();
        preparedStatement.close();

        disconnect();

        return true;
    }
	
	public List<Transaction> transactionList(String user) throws SQLException {
		List<Transaction> resultTrans = new ArrayList<Transaction>();
		String sql = "SELECT * FROM transactions WHERE origin = '" + user + "' AND transtype = 1";
		connect_func();

		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			Transaction trans = new Transaction();
			trans.setTransid(results.getInt("transid"));
			trans.setOrigin(results.getString("origin"));
			trans.setNftid(results.getInt("nftid"));
			trans.setRecipient(results.getString("recipient"));
			trans.setTranstype(results.getInt("transtype"));
			trans.setTimestamp(results.getString("timestamp"));
			trans.setPrice(results.getDouble("price"));

			resultTrans.add(trans);
		}

		results.close();
		disconnect();

		return resultTrans;
	}
	
    public List<Transaction> getBought(String user) throws SQLException {
        List<Transaction> resultTransactions = new ArrayList<Transaction>();
        String sql = "SELECT * FROM Transactions WHERE recipient='"+user+"' AND transtype=1";
        connect_func();

		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			Transaction trans = new Transaction();
			trans.setTransid(results.getInt("transid"));
			trans.setOrigin(results.getString("origin"));
			trans.setNftid(results.getInt("nftid"));
			trans.setRecipient(results.getString("recipient"));
			trans.setTranstype(results.getInt("transtype"));
			trans.setTimestamp(results.getString("timestamp"));
			trans.setPrice(results.getDouble("price"));
			
			resultTransactions.add(trans);
		}
		
		results.close();
		disconnect();
		
		for(Transaction trans : resultTransactions) {
			trans.setNftName(this.searchNFT(trans.getNftid()).get(0).getName());
		}
        return resultTransactions;
    }
    
    public List<Transaction> getSold(String user) throws SQLException {
        List<Transaction> resultTransactions = new ArrayList<Transaction>();
        String sql = "SELECT * FROM Transactions WHERE origin='"+user+"' AND transtype=1";
        connect_func();

		statement = (Statement) connect.createStatement();
		ResultSet results = statement.executeQuery(sql);

		while (results.next()) {
			Transaction trans = new Transaction();
			trans.setTransid(results.getInt("transid"));
			trans.setOrigin(results.getString("origin"));
			trans.setNftid(results.getInt("nftid"));
			trans.setRecipient(results.getString("recipient"));
			trans.setTranstype(results.getInt("transtype"));
			trans.setTimestamp(results.getString("timestamp"));
			trans.setPrice(results.getDouble("price"));
			
			resultTransactions.add(trans);
		}

		results.close();
		disconnect();
		
		for(Transaction trans : resultTransactions) {
			trans.setNftName(this.searchNFT(trans.getNftid()).get(0).getName());
		}

        return resultTransactions;
    }
    
    public List<NFT> searchNFT(int nftid) throws SQLException
    {
        List<NFT> resultNFTs = new ArrayList<NFT>();
        String sql = "SELECT * FROM NFTs WHERE nftid ='" + nftid + "'";
        
        connect_func();
        
        statement = (Statement) connect.createStatement();
        ResultSet results = statement.executeQuery(sql);
        
        while (results.next()){
            NFT nft = new NFT();
            nft.setNftid(results.getString("nftid"));
            nft.setName(results.getString("name"));
            nft.setUrl(results.getString("url"));
            nft.setCreator(results.getString("creator"));
            nft.setOwner(results.getString("owner"));
            nft.setMintTime(results.getString("mintTime"));
            
            resultNFTs.add(nft);
        }
        
        results.close();
        disconnect();
        
        return resultNFTs;
    }

	public void init() throws SQLException, FileNotFoundException, IOException {
		connect_func();
		statement = (Statement) connect.createStatement();

		String[] INITIAL = { "drop database if exists testdb; ", "create database testdb; ", "use testdb; ",
				"DROP TABLE IF EXISTS User; ", "DROP TABLE IF EXISTS NFTs; ", "DROP TABLE IF EXISTS Transactions; ",
				"DROP TABLE IF EXISTS Listings; ",
				("CREATE TABLE IF NOT EXISTS User(" + "email VARCHAR(50) NOT NULL, "
						+ "firstName VARCHAR(10) NOT NULL, " + "lastName VARCHAR(10) NOT NULL, "
						+ "password VARCHAR(20) NOT NULL, " + "birthday DATE NOT NULL, "
								+ "balance DOUBLE DEFAULT 100, "
						+ "adress_street_num VARCHAR(4) , " + "adress_street VARCHAR(30) , "
						+ "adress_city VARCHAR(20)," + "adress_state VARCHAR(2)," + "adress_zip_code VARCHAR(5),"
								+ ""
						+ "PRIMARY KEY (email) " + ");"),
				("CREATE TABLE IF NOT EXISTS Transactions(" + "transid INT AUTO_INCREMENT NOT NULL, "
						+ "origin VARCHAR(100), " + "recipient VARCHAR(100), " + "transtype CHAR(1), "
						+ "timestamp DATETIME, " + "price DOUBLE, "
								+ "nftid INT, " + "PRIMARY KEY(transid), "
						+ "FOREIGN KEY(origin) REFERENCES User(email), "
						+ "FOREIGN KEY(recipient) REFERENCES User(email)" + ");"),
				("CREATE TABLE IF NOT EXISTS NFTs(" + "nftid INT AUTO_INCREMENT NOT NULL, " + "name VARCHAR(100), "
						+ "url VARCHAR(200), " + "creator VARCHAR(100), " + "owner VARCHAR(100), "
						+ "mintTime DATETIME, " + "description VARCHAR(500), " + "PRIMARY KEY(nftid) " + ");"),
				("CREATE TABLE IF NOT EXISTS Listings(" + "listid INT AUTO_INCREMENT NOT NULL, "
						+ "owner VARCHAR(100), " + "nftid INT, " + "start DATETIME, " + "end DATETIME, "
						+ "price DOUBLE, " + "PRIMARY KEY(listid), " + "FOREIGN KEY(owner) REFERENCES User(email), "
						+ "FOREIGN KEY(nftid) REFERENCES NFTS(nftid) " + ");") };
		String[] TUPLES = {
				("insert into User(email, firstName, lastName, password, birthday, balance, adress_street_num, adress_street, adress_city, adress_state, adress_zip_code)"
						+ "values ('susie@gmail.com', 'Susie ', 'Guzman', 'susie1234', '2000-06-27', 100, '1234', 'whatever street', 'detroit', 'MI', '48202'),"
						+ "('root', 'default', 'default','pass1234', '0000-00-00', 100, '0000', 'Default', 'Default', '0', '00000'"
						+ ");") };

		// for loop to put these in database
		for (int i = 0; i < INITIAL.length; i++)
			statement.execute(INITIAL[i]);
		for (int i = 0; i < TUPLES.length; i++)
			statement.execute(TUPLES[i]);
		disconnect();
	}

}
