package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.LeaveApplicationController;
import exception.ItemNotAvailableForUpdateException;
import model.LeaveApplication;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JComboBox;

public class UpdateLeave {

	private JFrame updateLeaveFrame;
	private JTextField leaveIDField;
	private JTextField employeeIDField;
	private JTextField typeField;
	private JTextField dateField;
	private JComboBox statusField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateLeave window = new UpdateLeave();
					window.updateLeaveFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateLeave() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		updateLeaveFrame = new JFrame();
		updateLeaveFrame.setTitle("Update Leave");
		updateLeaveFrame.setBounds(100, 100, 520, 400);
		updateLeaveFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateLeaveFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 25);
		updateLeaveFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel searchInformationLabel = new JLabel("Search Information: ");
		searchInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		searchInformationLabel.setBounds(0, 0, 205, 25);
		panel.add(searchInformationLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 40, 486, 60);
		updateLeaveFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel leaveIDLabel = new JLabel("Leave ID: ");
		leaveIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		leaveIDLabel.setBounds(40, 26, 83, 13);
		panel_1.add(leaveIDLabel);

		leaveIDField = new JTextField();
		leaveIDField.setBounds(130, 23, 200, 19);
		panel_1.add(leaveIDField);
		leaveIDField.setColumns(10);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Verdana", Font.BOLD, 10));
		searchButton.setBounds(351, 22, 85, 21);
		panel_1.add(searchButton);

		// set action listener for search button
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {

					// get leave ID from field
					int leaveID = Integer.parseInt(leaveIDField.getText());

					// create leave application controller and get leave application
					LeaveApplicationController leaveController = new LeaveApplicationController();
					LeaveApplication leave = leaveController.searchLeaveApplicationByID(leaveID);

					// set leave application object with field
					employeeIDField.setText(Integer.toString(leave.getEmployeeID()));
					typeField.setText(leave.getType());
					dateField.setText(leave.getDate().toString());
					statusField.setSelectedItem(leave.getStatus());

				} catch (NullPointerException nullException) {
					new JOptionPane().showMessageDialog(null, "Leave application does not exist");
				} catch (NumberFormatException numberException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for number value. Please try again");
				} catch (ItemNotAvailableForUpdateException itemNotAvailableException) {
					new JOptionPane().showMessageDialog(null, itemNotAvailableException.getMessage());
				}

			}

		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 105, 486, 25);
		updateLeaveFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel leaveInformationLabel = new JLabel("Leave Information: ");
		leaveInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		leaveInformationLabel.setBounds(0, 0, 195, 25);
		panel_2.add(leaveInformationLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 293, 486, 60);
		updateLeaveFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Verdana", Font.BOLD, 10));
		resetButton.setBounds(49, 10, 85, 21);
		panel_3.add(resetButton);

		// set action listener for reset button
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// reset fields
				leaveIDField.setText("");
				employeeIDField.setText("");
				typeField.setText("");
				dateField.setText("");
				statusField.setSelectedIndex(0);

			}

		});

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(350, 10, 85, 21);
		panel_3.add(goBackButton);

		// set action listener for go back button
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				updateLeaveFrame.dispose();
				new OptionUser().main(null);

			}

		});

		JButton updateButton = new JButton("Update");
		updateButton.setFont(new Font("Verdana", Font.BOLD, 10));
		updateButton.setBounds(200, 10, 85, 21);
		panel_3.add(updateButton);

		// set action listener for update button
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					// create leave application controller object
					LeaveApplicationController leaveController = new LeaveApplicationController();

					// create and set leave application object with field
					LeaveApplication leave = new LeaveApplication();
					leave.setLeaveID(Integer.parseInt(leaveIDField.getText()));
					leave.setEmployeeID(Integer.parseInt(employeeIDField.getText()));
					leave.setType(typeField.getText());
					leave.setStatus((String) statusField.getSelectedItem());
					leave.setDate(Date.valueOf(dateField.getText()));

					// update leave application
					leaveController.updateLeaveApplication(leave);

				} catch (NullPointerException nullException) {
					new JOptionPane().showMessageDialog(null, "Please enter the required fields.");
				} catch (NumberFormatException numberException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for number value. Please try again");
				} catch (IllegalArgumentException dateException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for date value. Please try again");
				}

			}

		});

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 135, 486, 152);
		updateLeaveFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JLabel employeeIDLabel = new JLabel("Employee ID: ");
		employeeIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		employeeIDLabel.setBounds(10, 0, 222, 77);
		panel_4.add(employeeIDLabel);

		JLabel dateLabel = new JLabel("Date: ");
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		dateLabel.setBounds(254, 0, 222, 77);
		panel_4.add(dateLabel);

		JLabel typeLabel = new JLabel("Type: ");
		typeLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		typeLabel.setBounds(10, 77, 222, 77);
		panel_4.add(typeLabel);

		JLabel statusLabel = new JLabel("Status: ");
		statusLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		statusLabel.setBounds(254, 77, 222, 77);
		panel_4.add(statusLabel);

		employeeIDField = new JTextField();
		employeeIDField.setBounds(100, 29, 130, 19);
		panel_4.add(employeeIDField);
		employeeIDField.setColumns(10);

		typeField = new JTextField();
		typeField.setBounds(100, 106, 130, 19);
		panel_4.add(typeField);
		typeField.setColumns(10);

		dateField = new JTextField();
		dateField.setBounds(310, 29, 155, 19);
		panel_4.add(dateField);
		dateField.setColumns(10);

		String[] choices = { "Processing", "Accept", "Reject" };
		statusField = new JComboBox(choices);
		statusField.setSelectedIndex(0);
		statusField.setBounds(310, 105, 155, 21);
		panel_4.add(statusField);
	}

}
