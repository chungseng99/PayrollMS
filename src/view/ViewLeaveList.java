package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.LeaveApplicationController;
import model.LeaveApplication;

public class ViewLeaveList {

	private JFrame viewLeaveListFrame;
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
					ViewLeaveList window = new ViewLeaveList();
					window.viewLeaveListFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewLeaveList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		viewLeaveListFrame = new JFrame();
		viewLeaveListFrame.setTitle("ViewLeaveList");
		viewLeaveListFrame.setBounds(100, 100, 520, 400);
		viewLeaveListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewLeaveListFrame.getContentPane().setLayout(null);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(400, 335, 85, 21);
		viewLeaveListFrame.getContentPane().add(goBackButton);

		// set action listener for go back button
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				viewLeaveListFrame.dispose();
				new OptionUser().main(null);

			}

		});

		JLabel leaveApplicationListLabel = new JLabel("Leave Application List");
		leaveApplicationListLabel.setBounds(165, 10, 190, 25);
		viewLeaveListFrame.getContentPane().add(leaveApplicationListLabel);
		leaveApplicationListLabel.setFont(new Font("Verdana", Font.BOLD, 15));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 486, 288);
		viewLeaveListFrame.getContentPane().add(scrollPane);

		leaveTable = new JTable();

		// create leave application controller object
		LeaveApplicationController leaveController = new LeaveApplicationController();
		// get leave list from leave application controller
		ArrayList<LeaveApplication> leaveList = leaveController.getLeaveApplicationList();

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
		leaveTable.setFont(new Font("Verdana", Font.PLAIN, 10));
		scrollPane.setViewportView(leaveTable);
	}
}
