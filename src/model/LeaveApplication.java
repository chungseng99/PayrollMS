package model;

import java.sql.Date;

public class LeaveApplication {

	private int leaveID;

	private int employeeID;

	private Date date;

	private String type;

	private String status;

	public LeaveApplication(int leaveID, int employeeID, Date date, String type, String status) {
		super();
		this.leaveID = leaveID;
		this.employeeID = employeeID;
		this.date = date;
		this.type = type;
		this.status = status;
	}

	public LeaveApplication() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the leaveID
	 */
	public int getLeaveID() {
		return leaveID;
	}

	/**
	 * @param leaveID the leaveID to set
	 */
	public void setLeaveID(int leaveID) {
		this.leaveID = leaveID;
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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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

}
