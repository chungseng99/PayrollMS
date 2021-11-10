package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.connection.DBController;
import model.User;

/**
 * This is class of User Controller
 * 
 * @author Group L
 *
 */
public class UserController {
	/**
	 * Database controller
	 */
	private DBController dbController;

	/**
	 * constructor of this controller class
	 */
	public UserController() {

		this.dbController = new DBController();

	}

	/**
	 * Function to insert user into database
	 * 
	 * @param user
	 * @throws NullPointerException
	 */
	public void insertUser(User user) throws NullPointerException {

		// if user do not enter any details
		if (user.getUserID().isEmpty() || user.getPassword().isEmpty() || user.getFullName().isEmpty()) {
			throw new NullPointerException();
		}

		try {

			// connect to database
			Connection con = dbController.getConnection();
			// initialize query
			String query = "insert into user values(?,?,?)";
			// create prepared statement object
			PreparedStatement preparedStatement = con.prepareStatement(query);

			// set value for prepared statement
			preparedStatement.setString(1, user.getUserID());
			preparedStatement.setString(2, user.getFullName());
			preparedStatement.setString(3, user.getPassword());

			// execute prepared statement
			int i = preparedStatement.executeUpdate();
			System.out.println(i + " rows inserted");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function to update user to database
	 * 
	 * @param user
	 */
	public void updateUser(User user) {

		try {

			// connect to database
			Connection con = dbController.getConnection();
			// initialize query
			String query = " update user set " + "full_name = ?," + "password = ? " + "where user_id = ?";
			// create prepared statement object
			PreparedStatement statement = con.prepareStatement(query);

			// set prepared statement value
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getUserID());

			// execute prepared statement
			int i = statement.executeUpdate();
			System.out.println(i + " row has been updated");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function to delete user from database
	 * 
	 * @param userID
	 */
	public void deleteUserByID(String userID) {

		Connection con;
		try {

			// initialize query
			String query = "delete from user where user_ID = ?";

			// connect to database
			con = dbController.getConnection();

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, userID);

			// execute prepared statement
			int i = statement.executeUpdate();
			System.out.println(i + " row deleted");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function to search by user ID from database
	 * 
	 * @param userID
	 * @return user object
	 */
	public User searchByUserID(String userID) {

		// create user object
		User user = null;

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from user where user_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, userID);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// initialize user object
				user = new User(result.getString(1), result.getString(2), result.getString(3));

			}

			return user;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Function to login
	 * 
	 * @param userID
	 * @param password
	 * @return boolean
	 * @throws NullPointerException
	 */
	public boolean login(String userID, String password) throws NullPointerException {

		// if user do not enter any details
		if (userID.isEmpty() || password.isEmpty()) {
			throw new NullPointerException();
		}

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from user where user_id =? and password = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, userID);
			statement.setString(2, password);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				return true;

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
