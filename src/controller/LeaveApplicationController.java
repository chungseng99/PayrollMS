package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.connection.DBController;
import exception.ItemNotAvailableForUpdateException;
import model.Employee;
import model.LeaveApplication;

/**
 * This is class of Leave Application Controller
 * 
 * @author Group L
 *
 */
public class LeaveApplicationController {

	/**
	 * Database controller
	 */
	private DBController dbController;

	/**
	 * Constructor of this controller class
	 */
	public LeaveApplicationController() {

		this.dbController = new DBController();

	}

	/**
	 * Function to insert leave application into database
	 * 
	 * @param leaveApplication
	 */
	public void insertLeaveApplication(LeaveApplication leaveApplication) {

		try {
			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "insert into leave_application(employee_id,date,type,status)" + " values(?,?,?,?)";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, leaveApplication.getEmployeeID());
			statement.setDate(2, leaveApplication.getDate());
			statement.setString(3, leaveApplication.getType());
			statement.setString(4, leaveApplication.getStatus());

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
	 * Function to update leave application into database
	 * 
	 * @param leaveApplication
	 */
	public void updateLeaveApplication(LeaveApplication leaveApplication) {

		try {
			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "update leave_application set " + "employee_id = ?," + "date = ?," + "type = ?,"
					+ "status = ?" + " where leave_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, leaveApplication.getEmployeeID());
			statement.setDate(2, leaveApplication.getDate());
			statement.setString(3, leaveApplication.getType());
			statement.setString(4, leaveApplication.getStatus());
			statement.setInt(5, leaveApplication.getLeaveID());

			// execute query
			int i = statement.executeUpdate();

			// if leave application status is accept
			if (leaveApplication.getStatus().equals("Accept")) {

				// create employee controller object
				EmployeeController employeeController = new EmployeeController();

				// get employee from database
				Employee employee = employeeController.searchByEmployeeID(leaveApplication.getEmployeeID());

				// if leave application is annual leave
				if (leaveApplication.getType().equals("Annual Leave")) {

					// reduce amount of annual leave for employee
					employee.setAnnualLeave(employee.getAnnualLeave() - 1);

					// if leave application is sick leave
				} else if (leaveApplication.getType().equals("Sick Leave")) {

					// reduce amount of sick leave for employee
					employee.setSickLeave(employee.getSickLeave() - 1);

				}

				// update employee
				employeeController.updateEmployee(employee);

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Function to delete leave by ID from database
	 * 
	 * @param leaveID
	 */
	public void deleteLeaveApplicationByID(int leaveID) {

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "delete from leave_application where leave_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, leaveID);

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
	 * Function to search leave application by ID from database
	 * 
	 * @param leaveID
	 * @return leave application
	 * @throws ItemNotAvailableForUpdateException
	 */
	public LeaveApplication searchLeaveApplicationByID(int leaveID) throws ItemNotAvailableForUpdateException {

		// create leave application object
		LeaveApplication leaveApplication = null;

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from leave_application where leave_id = ?";

			// create and set prepared statement
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, leaveID);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// create leave application object
				leaveApplication = getLeaveApplicationFromResult(result);

				// if leave application status is accept
				if (leaveApplication.getStatus().equals("Accept")) {

					throw new ItemNotAvailableForUpdateException();

				}

				return leaveApplication;

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Function to get leave application from result
	 * 
	 * @param result
	 * @return leave application
	 * @throws SQLException
	 */
	private LeaveApplication getLeaveApplicationFromResult(ResultSet result) throws SQLException {

		LeaveApplication leaveApplication;
		leaveApplication = new LeaveApplication();
		leaveApplication.setLeaveID(result.getInt(1));
		leaveApplication.setEmployeeID(result.getInt(2));
		leaveApplication.setDate(result.getDate(3));
		leaveApplication.setType(result.getString(4));
		leaveApplication.setStatus(result.getString(5));
		return leaveApplication;
	}

	/**
	 * Function to get leave application list
	 * 
	 * @return leave application list
	 */
	public ArrayList<LeaveApplication> getLeaveApplicationList() {

		// create leave application list
		ArrayList<LeaveApplication> leaveApplicationList = new ArrayList<LeaveApplication>();

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from leave_application";

			// create prepared statement
			PreparedStatement statement = con.prepareStatement(query);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// add to leave application list
				leaveApplicationList.add(getLeaveApplicationFromResult(result));

			}

			return leaveApplicationList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Function to get approved leave application list by employee ID
	 * 
	 * @param employeeID
	 * @return leave application list
	 */
	public ArrayList<LeaveApplication> getApprovedLeaveApplicationListByEmployeeID(int employeeID) {

		// create leave application list
		ArrayList<LeaveApplication> leaveApplicationList = new ArrayList<LeaveApplication>();

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from leave_application where employee_id = ? and status = 'approved'";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, employeeID);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// add to leave application list
				leaveApplicationList.add(getLeaveApplicationFromResult(result));

			}

			return leaveApplicationList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Function to get leave application list by employee ID from database
	 * 
	 * @param employeeID
	 * @return leave application list
	 */
	public ArrayList<LeaveApplication> getLeaveApplicationListByEmployeeID(int employeeID) {

		// create leave application list
		ArrayList<LeaveApplication> leaveApplicationList = new ArrayList<LeaveApplication>();

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from leave_application where employee_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, employeeID);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// add to leave application list
				leaveApplicationList.add(getLeaveApplicationFromResult(result));

			}

			return leaveApplicationList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
