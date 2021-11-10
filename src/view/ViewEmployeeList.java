package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EmployeeController;
import model.Employee;

public class ViewEmployeeList {

	private JFrame frmViewEmployeeList;
	private JTable employeeTable;
	private String[] cols = { "Employee ID", "Employee Name", "Phone No.", "Gender", "Age", "Account No. ",
			"Date Joined", "Position", "Salary", "Sick Leave", "Annual Leave" };
	private String[][] data = {};
	private DefaultTableModel model = new DefaultTableModel(data, cols);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEmployeeList window = new ViewEmployeeList();
					window.frmViewEmployeeList.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewEmployeeList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmViewEmployeeList = new JFrame();
		frmViewEmployeeList.setTitle("View Employee List");
		frmViewEmployeeList.setBounds(100, 100, 1120, 560);
		frmViewEmployeeList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmViewEmployeeList.getContentPane().setLayout(null);

		JLabel employeeListLabel = new JLabel("Employee List");
		employeeListLabel.setBounds(525, 12, 126, 23);
		frmViewEmployeeList.getContentPane().add(employeeListLabel);
		employeeListLabel.setFont(new Font("Verdana", Font.BOLD, 15));

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1086, 25);
		frmViewEmployeeList.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 1086, 431);
		frmViewEmployeeList.getContentPane().add(scrollPane);

		// set value for table model
		employeeTable = new JTable();
		EmployeeController employeeController = new EmployeeController();
		ArrayList<Employee> employeeList = employeeController.getEmployeeList();
		for (Employee employee : employeeList) {

			int employeeID = employee.getEmployeeID();
			String employeeName = employee.getEmployeeName();
			String employeePosition = employee.getEmployeePosition();
			double employeeSalary = employee.getEmployeeSalary();
			int annualLeave = employee.getAnnualLeave();
			int sickLeave = employee.getSickLeave();
			String gender = employee.getGender();
			int accountNo = employee.getAccountNo();
			Date dateJoined = employee.getDateJoined();
			int age = employee.getAge();
			String phoneNo = employee.getPhoneNo();

			Object[] row = { employeeID, employeeName, phoneNo, gender, age, accountNo, dateJoined, employeePosition,
					employeeSalary, sickLeave, annualLeave };
			model.addRow(row);

		}
		employeeTable.setModel(model);
		employeeTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		employeeTable.getColumnModel().getColumn(2).setPreferredWidth(110);
		employeeTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		employeeTable.getColumnModel().getColumn(5).setPreferredWidth(130);
		employeeTable.getColumnModel().getColumn(6).setPreferredWidth(105);
		employeeTable.getColumnModel().getColumn(7).setPreferredWidth(105);
		employeeTable.getColumnModel().getColumn(8).setPreferredWidth(80);
		employeeTable.getColumnModel().getColumn(9).setPreferredWidth(85);
		employeeTable.getColumnModel().getColumn(10).setPreferredWidth(85);
		employeeTable.setFont(new Font("Verdana", Font.PLAIN, 10));
		scrollPane.setViewportView(employeeTable);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 472, 1086, 41);
		frmViewEmployeeList.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(991, 10, 85, 21);
		panel_1.add(goBackButton);

		// set action listener for go back button
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				frmViewEmployeeList.dispose();
				new OptionUser().main(null);

			}

		});
	}
}
