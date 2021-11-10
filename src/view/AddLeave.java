package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.LeaveApplicationController;
import model.LeaveApplication;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class AddLeave {

	private JFrame addLeaveFrame;
	private JTextField dateField;
	private JTextField employeeIDField;
	private JComboBox typeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddLeave window = new AddLeave();
					window.addLeaveFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddLeave() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addLeaveFrame = new JFrame();
		addLeaveFrame.setTitle("Add Leave");
		addLeaveFrame.setBounds(100, 100, 520, 400);
		addLeaveFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addLeaveFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 35);
		addLeaveFrame.getContentPane().add(panel);
		panel.setLayout(null);

		dateField = new JTextField();
		dateField.setText("");
		dateField.setBounds(341, 10, 135, 19);
		panel.add(dateField);
		dateField.setColumns(10);

		JLabel dateLabel = new JLabel("Date: ");
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		dateLabel.setBounds(286, 13, 45, 13);
		panel.add(dateLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 55, 486, 25);
		addLeaveFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel employeeInformationLabel = new JLabel("Employee Information: ");
		employeeInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		employeeInformationLabel.setBounds(0, 0, 205, 25);
		panel_1.add(employeeInformationLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 90, 486, 60);
		addLeaveFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel employeeIDLabel = new JLabel("Employee ID: ");
		employeeIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		employeeIDLabel.setBounds(85, 23, 85, 13);
		panel_2.add(employeeIDLabel);

		employeeIDField = new JTextField();
		employeeIDField.setBounds(170, 20, 210, 19);
		panel_2.add(employeeIDField);
		employeeIDField.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 160, 486, 25);
		addLeaveFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel leaveInformationLabel = new JLabel("Leave Information: ");
		leaveInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		leaveInformationLabel.setBounds(0, 0, 172, 25);
		panel_3.add(leaveInformationLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 195, 486, 60);
		addLeaveFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JLabel typeLabel = new JLabel("Type: ");
		typeLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		typeLabel.setBounds(105, 30, 45, 13);
		panel_4.add(typeLabel);

		String[] choices = { "Annual Leave", "Sick Leave" };
		typeField = new JComboBox(choices);
		typeField.setSelectedIndex(0);
		typeField.setBounds(170, 26, 210, 21);
		panel_4.add(typeField);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 265, 486, 60);
		addLeaveFrame.getContentPane().add(panel_5);
		panel_5.setLayout(null);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(349, 29, 85, 21);
		panel_5.add(goBackButton);

		// set action listener for goBackButton
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				addLeaveFrame.dispose();
				new OptionApply().main(null);

			}

		});

		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Verdana", Font.BOLD, 10));
		resetButton.setBounds(49, 29, 85, 21);
		panel_5.add(resetButton);

		// set action listener for resetButton
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// reset fields
				dateField.setText("");
				employeeIDField.setText("");
				typeField.setSelectedIndex(0);

			}

		});

		JButton applyButton = new JButton("Apply");
		applyButton.setFont(new Font("Verdana", Font.BOLD, 10));
		applyButton.setBounds(199, 29, 85, 21);
		panel_5.add(applyButton);

		// set action listener for applyButton
		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					// create and set leave application object from fields
					LeaveApplication leaveApplication = new LeaveApplication();
					leaveApplication.setDate(Date.valueOf(dateField.getText()));
					leaveApplication.setEmployeeID(Integer.parseInt(employeeIDField.getText()));
					leaveApplication.setType((String) typeField.getSelectedItem());
					leaveApplication.setStatus("Processing");

					// create leave application controller
					LeaveApplicationController leaveApplicationController = new LeaveApplicationController();

					// insert leave application
					leaveApplicationController.insertLeaveApplication(leaveApplication);

				} catch (NullPointerException nullException) {
					new JOptionPane().showMessageDialog(null, "Please enter the required fields.");
				} catch (NumberFormatException numberException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for number value. Please try again");
				} catch (IllegalArgumentException dateException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for date value. Please try again");
				}
			}
		});
	}

}
