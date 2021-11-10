package model;

import java.sql.Date;

public class Claim {

	private int claimID;

	private int employeeID;

	private double amount;

	private String status;

	private Date date;

	public Claim() {
	}

	/**
	 * @return the claimID
	 */
	public int getClaimID() {
		return claimID;
	}

	/**
	 * @param claimID the claimID to set
	 */
	public void setClaimID(int claimID) {
		this.claimID = claimID;
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
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	};

}
