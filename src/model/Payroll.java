package model;

import java.sql.Date;

public class Payroll {

	private int payrollID;

	private int employeeID;

	private double totalAddition;

	private double totalDeduction;

	private double totalAmount;

	private Date date;

	public Payroll(int payrollID, int employeeID, double totalClaim, double totalDeduction, double totalAmount,
			Date date) {
		super();
		this.payrollID = payrollID;
		this.employeeID = employeeID;
		this.totalAddition = totalClaim;
		this.totalDeduction = totalDeduction;
		this.totalAmount = totalAmount;
		this.date = date;
	}

	public Payroll() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the payrollID
	 */
	public int getPayrollID() {
		return payrollID;
	}

	/**
	 * @param payrollID the payrollID to set
	 */
	public void setPayrollID(int payrollID) {
		this.payrollID = payrollID;
	}

	/**
	 * @return the employeeID
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * @param employeeID the employeeID to set
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return the totalAddition
	 */
	public double getTotalAddition() {
		return totalAddition;
	}

	/**
	 * @param totalAddition the totalAddition to set
	 */
	public void setTotalAddition(double totalAddition) {
		this.totalAddition = totalAddition;
	}

	/**
	 * @return the totalDeduction
	 */
	public double getTotalDeduction() {
		return totalDeduction;
	}

	/**
	 * @param totalDeduction the totalDeduction to set
	 */
	public void setTotalDeduction(double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

}
