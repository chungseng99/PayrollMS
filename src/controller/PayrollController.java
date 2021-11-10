package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import database.connection.DBController;
import model.Claim;
import model.Employee;
import model.Payroll;

/**
 * This is class of Payroll Controller
 * 
 * @author Group L
 *
 */
public class PayrollController {

	/**
	 * Database controller
	 */
	private DBController dbController;

	/**
	 * Constructor of this controller class
	 */
	public PayrollController() {

		this.dbController = new DBController();

	}

	/**
	 * Function to insert payroll into database
	 * 
	 * @param payroll
	 * @return payroll ID
	 */
	public int insertPayroll(Payroll payroll) {

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "insert into payroll(employee_id,"
					+ "total_addition,total_deduction,total_amount,date) values(?,?,?,?,?)";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, payroll.getEmployeeID());
			statement.setDouble(2, payroll.getTotalAddition());
			statement.setDouble(3, payroll.getTotalDeduction());
			statement.setDouble(4, payroll.getTotalAmount());
			statement.setDate(5, payroll.getDate());

			// execute query and get result set
			int i = statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();

			// if result is not empty
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * Function to update payroll into database
	 * 
	 * @param payroll
	 */
	public void updatePayroll(Payroll payroll) {

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "update payroll set " + "employee_id = ?," + "total_addition = ?," + "total_deduction = ?,"
					+ "total_amount = ?," + "date = ?" + " where payroll_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, payroll.getEmployeeID());
			statement.setDouble(2, payroll.getTotalAddition());
			statement.setDouble(3, payroll.getTotalDeduction());
			statement.setDouble(4, payroll.getTotalAmount());
			statement.setDate(5, payroll.getDate());
			statement.setInt(6, payroll.getPayrollID());

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
	 * Function to delete payroll by ID from database
	 * 
	 * @param payrollID
	 */
	public void deletePayrollByID(int payrollID) {

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "delete from payroll where payroll_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, payrollID);

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
	 * Function to search payroll by ID from database
	 * 
	 * @param payrollID
	 * @return payroll
	 */
	public Payroll searchPayrollByID(int payrollID) {

		// create payroll object
		Payroll payroll = null;

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from payroll where payroll_id = ?";

			// create and set prepared statement object
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, payrollID);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// get payroll result
				payroll = getPayrollFromResult(result);

				return payroll;

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Function to get payroll from result
	 * 
	 * @param result
	 * @return payroll
	 * @throws SQLException
	 */
	private Payroll getPayrollFromResult(ResultSet result) throws SQLException {

		Payroll payroll;
		payroll = new Payroll();
		payroll.setPayrollID(result.getInt(1));
		payroll.setEmployeeID(result.getInt(2));
		payroll.setTotalAddition(result.getDouble(3));
		payroll.setTotalDeduction(result.getDouble(4));
		payroll.setTotalAmount(result.getDouble(5));
		payroll.setDate(result.getDate(6));
		return payroll;
	}

	/**
	 * Function to get list of payroll
	 * 
	 * @return payroll list
	 */
	public ArrayList<Payroll> getListOfPayroll() {

		// create payroll list
		ArrayList<Payroll> payrollList = new ArrayList<Payroll>();

		try {

			// connect to database
			Connection con = dbController.getConnection();

			// initialize query
			String query = "select * from payroll";

			// create prepared statement object
			PreparedStatement statement = con.prepareStatement(query);

			// execute query and get result set
			ResultSet result = statement.executeQuery();

			// while result is not empty
			while (result.next()) {

				// add result to payroll list
				payrollList.add(getPayrollFromResult(result));

			}

			return payrollList;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Function to generate payroll
	 * 
	 * @param employeeID
	 * @param hour
	 */
	public void generatePayroll(int employeeID, int hour) {

		// create employee controller and claim controller object
		EmployeeController employeeController = new EmployeeController();
		ClaimController claimController = new ClaimController();

		// get employee from employee controller
		Employee employee = employeeController.searchByEmployeeID(employeeID);

		// get approved claim list from claim controller
		ArrayList<Claim> approvedClaimList = claimController.getApprovedClaimListByEmployeeID(employeeID);

		// get claim list from claim controller
		ArrayList<Claim> claimList = claimController.getClaimListByEmployeeID(employeeID);

		// set total claim, total deduction and total leave
		double totalClaim = 0;
		double totalDeduction = 0;
		int totalLeave = 0;

		// calculate total claim
		for (Claim claim : approvedClaimList) {

			totalClaim += claim.getAmount();

		}

		// update all claim
		for (Claim claim : claimList) {

			claim.setStatus("Archived");
			claimController.updateClaim(claim);

		}

		// if annual leave is less than 0
		if (employee.getAnnualLeave() < 0) {
			// update total deduction and annual leave
			int annualLeave = Math.abs(employee.getAnnualLeave());
			totalDeduction += annualLeave * 50;
			totalLeave += annualLeave;
			employee.setAnnualLeave(0);
		}

		// if sick leave is less than 0
		if (employee.getSickLeave() < 0) {
			// update total deduction and sick leave
			int sickLeave = Math.abs(employee.getSickLeave());
			totalDeduction += sickLeave * 50;
			totalLeave += sickLeave;
			employee.setSickLeave(0);
		}

		// calculate epf, sosco, hour pay and total OT pay
		double epf = Math.round(employee.getEmployeeSalary() * 0.08 * 100.0) / 100.0;
		double sosco = Math.round(employee.getEmployeeSalary() * 0.005 * 100.0) / 100.0;
		totalDeduction = epf + sosco;
		double hourPay = employee.getEmployeeSalary() / 168;
		double totalOTPay = Math.round(hourPay * 1.5 * hour * 100.0) / 100.0;

		// update total claim
		totalClaim += totalOTPay;

		// update employee
		employeeController.updateEmployee(employee);

		// calculate total amount
		double totalAmount = employee.getEmployeeSalary();
		totalAmount = totalAmount + totalClaim - totalDeduction;

		// create and set payroll object
		Payroll payroll = new Payroll();
		payroll.setEmployeeID(employeeID);
		payroll.setTotalAmount(totalAmount);
		payroll.setTotalAddition(Math.round(totalClaim * 100.0) / 100.0);
		payroll.setTotalDeduction(totalDeduction);
		payroll.setDate(Date.valueOf(java.time.LocalDate.now()));

		// insert payroll
		int id = insertPayroll(payroll);

		// set payroll ID
		payroll.setPayrollID(id);

		// create and generate excel
		ExcelController excelController = new ExcelController();
		excelController.generateExcelPayslip(payroll, approvedClaimList, hour, totalLeave);

	}
}
