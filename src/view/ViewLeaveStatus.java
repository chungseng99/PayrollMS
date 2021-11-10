package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.LeaveApplicationController;
import model.LeaveApplication;

public class ViewLeaveStatus {

	private JFrame viewLeaveStatusFrame;
	private JTextField employeeIDField;
	private JTable leaveTable;
	private String[] cols = { "Leave ID", "Employee ID", "Type", "Date", "Status" };
	private String[][] data = {};
	private DefaultTableModel model = new DefaultTableModel(data, cols);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLeaveStatus window = new ViewLeaveStatus();
					window.viewLeaveStatusFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewLeaveStatus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		viewLeaveStatusFrame = new JFrame();
		viewLeaveStatusFrame.setTitle("View Leave Status");
		viewLeaveStatusFrame.setBounds(100, 100, 520, 400);
		viewLeaveStatusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewLeaveStatusFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 25);
		viewLeaveStatusFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel employeeInfomationLabel = new JLabel("Employee Information: ");
		employeeInfomationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		employeeInfomationLabel.setBounds(0, 0, 205, 25);
		panel.add(employeeInfomationLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 40, 486, 65);
		viewLeaveStatusFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel employeeIDLabel = new JLabel("Employee ID: ");
		employeeIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		employeeIDLabel.setBounds(50, 24, 82, 13);
		panel_1.add(employeeIDLabel);

		employeeIDField = new JTextField();
		employeeIDField.setBounds(142, 21, 194, 19);
		panel_1.add(employeeIDField);
		employeeIDField.setColumns(10);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Verdana", Font.BOLD, 10));
		searchButton.setBounds(346, 20, 85, 21);
		panel_1.add(searchButton);

		// set action listener for search button
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					// get employee ID from field
					int employeeIDFieldValue = Integer.parseInt(employeeIDField.getText());

					// create leave application controller object
					LeaveApplicationController leaveApplicationController = new LeaveApplicationController();

					// get leave list from leave application controller
					ArrayList<LeaveApplication> leaveList = leaveApplicationController
							.getLeaveApplicationListByEmployeeID(employeeIDFieldValue);

					model = new DefaultTableModel(data, cols);

					// set value for table model
					for (LeaveApplication leave : leaveList) {

						int leaveID = leave.getLeaveID();
						int employeeID = leave.getEmployeeID();
						Date date = leave.getDate();
						String type = leave.getType();
						String status = leave.getStatus();

						Object[] row = { leaveID, employeeID, type, date, status };
						model.addRow(row);

					}

					leaveTable.setModel(model);

				} catch (NullPointerException nullException) {
					new JOptionPane().showMessageDialog(null, "Please enter the required fields.");
				} catch (NumberFormatException numberException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for number value. Please try again");
				}

			}

		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 115, 486, 25);
		viewLeaveStatusFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel leaveInformationLabel = new JLabel("Leave Information: ");
		leaveInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		leaveInformationLabel.setBounds(0, 0, 172, 25);
		panel_2.add(leaveInformationLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 288, 486, 65);
		viewLeaveStatusFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(391, 22, 85, 21);
		panel_3.add(goBackButton);

		// set action listener for go back button
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				viewLeaveStatusFrame.dispose();
				new OptionApply().main(null);
			}

		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 486, 136);
		viewLeaveStatusFrame.getContentPane().add(scrollPane);

		leaveTable = new JTable();
		leaveTable.setModel(model);
		scrollPane.setViewportView(leaveTable);
	}
}
