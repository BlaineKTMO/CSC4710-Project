import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private userDAO userDAO = new userDAO();
	private String currentUser;
	private HttpSession session = null;

	public ControlServlet() {

	}

	public void init() {
		userDAO = new userDAO();
		currentUser = "";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		try {
			switch (action) {
				case "/login":
					login(request, response);
					break;
				case "/register":
					register(request, response);
					break;
				case "/initialize":
					userDAO.init();
					System.out.println("Database successfully initialized!");
					rootPage(request, response, "");
					break;
				case "/root":
					rootPage(request, response, "");
					break;
				case "/logout":
					logout(request, response);
					break;
				case "/list":
					System.out.println("The action is: list");
					listUser(request, response);
					break;
				case "/mint":
					mint(request, response);
					break;
				case "/createListing":
					createListing(request, response);
					break;
				case "/submitListing":
					submitListing(request, response);
					break;
				case "/viewListings":
					viewListings(request, response);
					break;
				case "/transferNFT":
					startTransferPage(request, response);
					break;
				case "/submitTransfer":
					submitTransfer(request, response);
					break;
				case "/searchNFT":
					searchNFT(request, response);
					break;
				case "/purchaseNFT":
					purchaseNFT(request, response);
					break;
				case "/creatorList":
					creatorList(request, response);
					break;
				case "/viewNFT":
					viewNFT(request, response);
					break;
				case "/searchUser":
					searchUser(request, response);
					break;
				case "/getBought":
					getBought(request, response);
					break;
				case "/getSold":
					getSold(request, response);
					break;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		System.out.println("listUser started: 00000000000000000000000000000000000");

		List<user> listUser = userDAO.listAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");
		dispatcher.forward(request, response);

		System.out.println("listPeople finished: 111111111111111111111111111111111111");
	}

	private void rootPage(HttpServletRequest request, HttpServletResponse response, String view)
			throws ServletException, IOException, SQLException {
		System.out.println("root view");
		request.setAttribute("listUser", userDAO.listAllUsers());
		request.getRequestDispatcher("rootView.jsp").forward(request, response);
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (email.equals("root") && password.equals("pass1234")) {
			System.out.println("Login Successful! Redirecting to root");
			session = request.getSession();
			session.setAttribute("username", email);
			rootPage(request, response, "");
		} else if (userDAO.isValid(email, password)) {

			currentUser = email;
			System.out.println("Login Successful! Redirecting");
			request.getRequestDispatcher("activitypage.jsp").forward(request, response);

		} else {
			request.setAttribute("loginStr", "Login Failed: Please check your credentials.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String email = request.getParameter("email");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String birthday = request.getParameter("birthday");
		String adress_street_num = request.getParameter("adress_street_num");
		String adress_street = request.getParameter("adress_street");
		String adress_city = request.getParameter("adress_city");
		String adress_state = request.getParameter("adress_state");
		String adress_zip_code = request.getParameter("adress_zip_code");
		String confirm = request.getParameter("confirmation");
		double balance = 100;

		if (password.equals(confirm)) {
			if (!userDAO.checkEmail(email)) {
				System.out.println("Registration Successful! Added to database");
				user users = new user(email, firstName, lastName, password, birthday, balance, adress_street_num, adress_street,
						adress_city, adress_state, adress_zip_code);
				userDAO.insert(users);
				response.sendRedirect("login.jsp");
			} else {
				System.out.println("Username taken, please enter new username");
				request.setAttribute("errorOne", "Registration failed: Username taken, please enter a new username.");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		} else {
			System.out.println("Password and Password Confirmation do not match");
			request.setAttribute("errorTwo", "Registration failed: Password and Password Confirmation do not match.");
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		currentUser = "";
		response.sendRedirect("login.jsp");
	}

	private void mint(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, SQLException, IOException {
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		String desc = request.getParameter("description");
		session = request.getSession();

		userDAO.mintNFT(name, image, currentUser, desc);

		request.getRequestDispatcher("activitypage.jsp").forward(request, response);
	}

	private void createListing(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<String> names = userDAO.getNFTNames();

		request.setAttribute("names", names);
		RequestDispatcher dispatcher = request.getRequestDispatcher("createListing.jsp");
		dispatcher.forward(request, response);
	}

	private void submitListing(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String nftName = request.getParameter("nft");

		String price = request.getParameter("price");
		String daysAvailable = request.getParameter("daysAvailable");

		session = request.getSession();

		userDAO.submitListing(nftName, currentUser, daysAvailable, price);
		request.getRequestDispatcher("listings.jsp").forward(request, response);

	}

	private void viewListings(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Listing> listings = userDAO.viewListings();

		session = request.getSession();
		session.setAttribute("listings", listings);

		request.getRequestDispatcher("listings.jsp").forward(request, response);

	}

	private void startTransferPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<String> names = userDAO.getNFTNames();
		List<String> users = userDAO.getUsernames();

		session = request.getSession();
		session.setAttribute("names", names);
		session.setAttribute("users", users);

		request.getRequestDispatcher("transferNFT.jsp").forward(request, response);
	}

	private void submitTransfer(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String nft = request.getParameter("nft");
		String targetUser = request.getParameter("user");

		userDAO.transferNFT(nft, targetUser, 0.);

		request.getRequestDispatcher("activitypage.jsp").forward(request, response);
	}

	private void searchNFT(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String nft = request.getParameter("targetName");
		List<NFT> resultList = userDAO.searchNFT(nft);

		session = request.getSession();
		session.setAttribute("nfts", resultList);

		request.getRequestDispatcher("searchResults.jsp").forward(request, response);
		// response.sendRedirect("searchResults.jsp");
	}

	private void purchaseNFT(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String nft = request.getParameter("nft");
		String nftid = userDAO.getNFTID(nft);
		double price = Double.parseDouble(userDAO.getPrice(nftid));
		System.out.println(price);
		String name;
		
		userDAO.changeBalance(currentUser, 0 - price);
		
		name = userDAO.searchNFT(nft).get(0).getOwner();
		userDAO.changeBalance(name, price);
		userDAO.transferNFT(nft, currentUser, price);
		
		
		request.getRequestDispatcher("activitypage.jsp").forward(request, response);
	}
	
	private void viewNFT(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String nft = request.getParameter("nft");
		List<NFT> resultList = userDAO.viewNFT(nft);
		
		session = request.getSession();
		session.setAttribute("nfts", resultList);
		
		request.getRequestDispatcher("nftView.jsp").forward(request, response);
	}
	
	private void creatorList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<NFT> resultList = userDAO.mintedList(currentUser);
		request.setAttribute("mintList", resultList);
		request.getRequestDispatcher("mintedList.jsp").forward(request, response);
	}
	
	private void searchUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String user = request.getParameter("user");
		List<user> resultList = userDAO.searchUser(user);
		List<NFT> nftList = userDAO.ownedNftList(user);
		
		session = request.getSession();
		session.setAttribute("user", resultList.get(0));
		session.setAttribute("nfts", nftList);
		
		request.getRequestDispatcher("viewUser.jsp").forward(request, response);
	}
	private void getBought(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Transaction> transactionList = userDAO.getBought(currentUser);
//		List<NFT> resultList = new ArrayList<NFT>();
//		for(Transaction trans : transactionList)
//		{
//			resultList.add(userDAO.searchNFT(trans.getNftid()).get(0));
//		}
		
		request.setAttribute("trans", transactionList);
		request.getRequestDispatcher("purchaseHistory.jsp").forward(request, response);
	}
	private void getSold(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		List<Transaction> transactionList = userDAO.getSold(currentUser);
//		List<NFT> resultList = new ArrayList<NFT>();
//		for(Transaction trans : transactionList)
//		{
//			resultList.add(userDAO.searchNFT(trans.getNftid()).get(0));
//		}
//		
		request.setAttribute("trans", transactionList);
		request.getRequestDispatcher("sellingHistory.jsp").forward(request, response);
	}
}
