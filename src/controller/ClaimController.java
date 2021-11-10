package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.connection.DBController;
import exception.ItemNotAvailableForUpdateException;
import model.Claim;

/**
 * This is class of Claim Controller
 * 
 * @author Group L
 *
 */
public class ClaimController {

	/**
	 * Database Controller
	 */
	private DBController dbController;

	/**
	 * Constructor of this controller class
	 */
	public ClaimController() {

		this.dbController = new DBController();

	}

	/**
	 * Function to insert claim into database
	 * 
	 * @param claim
	 */
	public void insertClaim(Claim claim) {

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "insert into claim(employee_id,amount,status,date)" + " values(?,?,?,?)";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, claim.getEmployeeID());
			statement.setDouble(2, claim.getAmount());
			statement.setString(3, claim.getStatus());
			statement.setDate(4, claim.getDate());

			// execute query
			int i = statement.executeUpdate();
			System.out.println(i + " row inserted");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function to update claim into database
	 * 
	 * @param claim
	 */
	public void updateClaim(Claim claim) {

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "update claim set " + "employee_id = ?," + "amount = ?," + "status = ?," + "date = ? "
					+ "where claim_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, claim.getEmployeeID());
			statement.setDouble(2, claim.getAmount());
			statement.setString(3, claim.getStatus());
			statement.setDate(4, claim.getDate());
			statement.setInt(5, claim.getClaimID());

			// execute query
			int i = statement.executeUpdate();
			System.out.println(i + " row updated");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function to delete claim by ID from database
	 * 
	 * @param claimID
	 */
	public void deleteClaimByID(int claimID) {

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "delete from claim where claim_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, claimID);

			// execute query
			int i = statement.executeUpdate();
			System.out.println(i + " row deleted");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function to search by ID from database
	 * 
	 * @param claimID
	 * @return claim
	 * @throws ItemNotAvailableForUpdateException
	 */
	public Claim searchClaimByID(int claimID) throws ItemNotAvailableForUpdateException {

		// create claim object
		Claim claim = null;

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from claim where claim_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, claimID);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// get claim from result
				claim = getClaimFromResult(result);

				// if claim status is archived or accept
				if (claim.getStatus().equals("Archived") || claim.getStatus().equals("Accept")) {
					throw new ItemNotAvailableForUpdateException();
				}

				return claim;

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Function to get claim result from database
	 * 
	 * @param result
	 * @return claim
	 * @throws SQLException
	 */
	private Claim getClaimFromResult(ResultSet result) throws SQLException {

		Claim claim = new Claim();
		claim.setClaimID(result.getInt(1));
		claim.setEmployeeID(result.getInt(2));
		claim.setAmount(result.getDouble(3));
		claim.setStatus(result.getString(4));
		claim.setDate(result.getDate(5));
		return claim;
	}

	/**
	 * Function to get claim list from database
	 * 
	 * @return
	 */
	public ArrayList<Claim> getClaimList() {

		// create claim list
		ArrayList<Claim> claimList = new ArrayList<Claim>();

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from claim";

			// create prepared statement object
			PreparedStatement statement = con.prepareStatement(query);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// if result is not empty
			while (result.next()) {

				// add result to claim list
				claimList.add(getClaimFromResult(result));

			}

			return claimList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Function to get approved claim list by searching employee ID
	 * 
	 * @param employeeID
	 * @return claim list
	 */
	public ArrayList<Claim> getApprovedClaimListByEmployeeID(int employeeID) {

		// create claim list
		ArrayList<Claim> claimList = new ArrayList<Claim>();

		try {
			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from claim where employee_id = ? and status = 'Accept'";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, employeeID);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// if result is not empty
			while (result.next()) {

				// add result to claim list
				claimList.add(getClaimFromResult(result));

			}

			return claimList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Function to get claim list by searching employee ID
	 * 
	 * @param employeeID
	 * @return
	 */
	public ArrayList<Claim> getClaimListByEmployeeID(int employeeID) {

		// create claim list
		ArrayList<Claim> claimList = new ArrayList<Claim>();

		try {
			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from claim where employee_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, employeeID);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// add result to claim list
				claimList.add(getClaimFromResult(result));

			}

			return claimList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
