package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import database.connection.DBController;
import model.Employee;

/**
 * This is class of Employee Controller
 * 
 * @author Group L
 *
 */
public class EmployeeController {

	/**
	 * Database controller
	 */
	private DBController dbController;

	/**
	 * constructor of this controller class
	 */
	public EmployeeController() {
		this.dbController = new DBController();
	}

	/**
	 * Function to insert employee into database
	 * 
	 * @param employee
	 */
	public void insertEmployee(Employee employee) {

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "insert into employee" + "(employee_name,employee_position,employee_salary,"
					+ "annual_leave, sick_leave, gender, account_no, date_joined, age, phone_no) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, employee.getEmployeeName());
			statement.setString(2, employee.getEmployeePosition());
			statement.setDouble(3, employee.getEmployeeSalary());
			statement.setInt(4, employee.getAnnualLeave());
			statement.setInt(5, employee.getSickLeave());
			statement.setString(6, employee.getGender());
			statement.setInt(7, employee.getAccountNo());
			statement.setDate(8, employee.getDateJoined());
			statement.setInt(9, employee.getAge());
			statement.setString(10, employee.getPhoneNo());

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
	 * Function to update employee into database
	 * 
	 * @param employee
	 */
	public void updateEmployee(Employee employee) {

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "update employee set " + " employee_name = ?," + " employee_position = ?,"
					+ " employee_salary = ?," + " annual_leave = ?," + " sick_leave = ?," + " gender = ?,"
					+ " account_no = ?," + " date_joined = ?," + " age = ?," + " phone_no = ?"
					+ " where employee_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, employee.getEmployeeName());
			statement.setString(2, employee.getEmployeePosition());
			statement.setDouble(3, employee.getEmployeeSalary());
			statement.setInt(4, employee.getAnnualLeave());
			statement.setInt(5, employee.getSickLeave());
			statement.setString(6, employee.getGender());
			statement.setInt(7, employee.getAccountNo());
			statement.setDate(8, employee.getDateJoined());
			statement.setInt(9, employee.getAge());
			statement.setString(10, employee.getPhoneNo());
			statement.setInt(11, employee.getEmployeeID());

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
	 * Function to delete employee by search ID from database
	 * 
	 * @param employeeID
	 */
	public void deleteEmployeeByID(int employeeID) {

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "delete from employee where employee_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, employeeID);

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
	 * Function to search by employee ID from database
	 * 
	 * @param employeeID
	 * @return employee
	 */
	public Employee searchByEmployeeID(int employeeID) {

		// create employee object
		Employee employee = null;

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from employee where employee_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, employeeID);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// get employee from result
				employee = getEmployeeFromResult(result);

			}

			return employee;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Function to get employee from result
	 * 
	 * @param result
	 * @return employee
	 * @throws SQLException
	 */
	private Employee getEmployeeFromResult(ResultSet result) throws SQLException {

		Employee employee;
		employee = new Employee();
		employee.setEmployeeID(result.getInt(1));
		employee.setEmployeeName(result.getString(2));
		employee.setEmployeePosition(result.getString(3));
		employee.setEmployeeSalary(result.getDouble(4));
		employee.setAnnualLeave(result.getInt(5));
		employee.setSickLeave(result.getInt(6));
		employee.setGender(result.getString(7));
		employee.setAccountNo(result.getInt(8));
		employee.setDateJoined(result.getDate(9));
		employee.setAge(result.getInt(10));
		employee.setPhoneNo(result.getString(11));

		return employee;
	}

	/**
	 * Function to get employee list from database
	 * 
	 * @return
	 */
	public ArrayList<Employee> getEmployeeList() {

		// create employee list
		ArrayList<Employee> employeeList = new ArrayList<Employee>();

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from employee";

			// create prepared statement
			PreparedStatement statement = con.prepareStatement(query);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// add result into employee list
				employeeList.add(getEmployeeFromResult(result));

			}

			return employeeList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
