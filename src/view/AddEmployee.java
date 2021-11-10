package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;

import controller.EmployeeController;
import model.Employee;

import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class AddEmployee {

	private JFrame addEmployeeFrame;
	private JTextField nameField;
	private JTextField accNoField;
	private JTextField positionField;
	private JTextField dateJoinedField;
	private JTextField salaryField;
	private JTextField phoneNoField;
	private JSpinner annualLeaveField;
	private JSpinner sickLeaveField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee window = new AddEmployee();
					window.addEmployeeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddEmployee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addEmployeeFrame = new JFrame();
		addEmployeeFrame.setTitle("Add Employee");
		addEmployeeFrame.setBounds(100, 100, 520, 400);
		addEmployeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addEmployeeFrame.getContentPane().setLayout(null);

		JLabel employeeInformationLabel = new JLabel("Employee Information: ");
		employeeInformationLabel.setBounds(10, 10, 205, 19);
		addEmployeeFrame.getContentPane().add(employeeInformationLabel);
		employeeInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 19);
		addEmployeeFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 27, 486, 208);
		addEmployeeFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(0, 0, 243, 54);
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		panel_1.add(nameLabel);

		JLabel phoneNoLabel = new JLabel("Phone No: ");
		phoneNoLabel.setBounds(243, 0, 243, 54);
		phoneNoLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		panel_1.add(phoneNoLabel);

		JLabel genderLabel = new JLabel("Gender: ");
		genderLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		genderLabel.setBounds(0, 54, 243, 54);
		panel_1.add(genderLabel);

		JLabel ageLabel = new JLabel("Age: ");
		ageLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		ageLabel.setBounds(243, 54, 243, 54);
		panel_1.add(ageLabel);

		JLabel accNoLabel = new JLabel("Acc No: ");
		accNoLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		accNoLabel.setBounds(0, 108, 243, 54);
		panel_1.add(accNoLabel);

		JLabel dateJoinedLabel = new JLabel("Date Join: ");
		dateJoinedLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		dateJoinedLabel.setBounds(243, 108, 243, 54);
		panel_1.add(dateJoinedLabel);

		JLabel positionLabel = new JLabel("Position: ");
		positionLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		positionLabel.setBounds(0, 162, 243, 54);
		panel_1.add(positionLabel);

		JLabel salaryLabel = new JLabel("Salary: ");
		salaryLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		salaryLabel.setBounds(243, 162, 243, 54);
		panel_1.add(salaryLabel);

		nameField = new JTextField();
		nameField.setBounds(60, 20, 165, 19);
		panel_1.add(nameField);
		nameField.setColumns(10);

		accNoField = new JTextField();
		accNoField.setBounds(60, 128, 165, 19);
		panel_1.add(accNoField);
		accNoField.setColumns(10);

		positionField = new JTextField();
		positionField.setBounds(60, 182, 165, 19);
		panel_1.add(positionField);
		positionField.setColumns(10);

		dateJoinedField = new JTextField();
		dateJoinedField.setBounds(321, 128, 155, 19);
		panel_1.add(dateJoinedField);
		dateJoinedField.setColumns(10);

		salaryField = new JTextField();
		salaryField.setBounds(321, 182, 155, 19);
		panel_1.add(salaryField);
		salaryField.setColumns(10);

		JSpinner ageField = new JSpinner();
		ageField.setBounds(321, 74, 155, 20);
		panel_1.add(ageField);

		phoneNoField = new JTextField();
		phoneNoField.setBounds(321, 20, 155, 19);
		panel_1.add(phoneNoField);
		phoneNoField.setColumns(10);

		String[] choices = { "Male", "Female" };
		JComboBox genderField = new JComboBox(choices);
		genderField.setBounds(60, 73, 165, 21);
		genderField.setSelectedIndex(0);
		panel_1.add(genderField);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 313, 486, 40);
		addEmployeeFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Verdana", Font.BOLD, 10));
		resetButton.setBounds(60, 10, 85, 21);
		panel_2.add(resetButton);

		// set action listener for resetButton
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// reset fields
				nameField.setText("");
				positionField.setText("");
				salaryField.setText("");
				genderField.setSelectedIndex(0);
				accNoField.setText("");
				phoneNoField.setText("");
				ageField.setValue(0);
				dateJoinedField.setText("");
				annualLeaveField.setValue(0);
				sickLeaveField.setValue(0);

			}

		});

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(345, 10, 85, 21);
		panel_2.add(goBackButton);

		// set action listener for goBackButton
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				addEmployeeFrame.dispose();
				new OptionUser().main(null);

			}

		});

		JButton addButton = new JButton("Add");
		addButton.setFont(new Font("Verdana", Font.BOLD, 10));
		addButton.setBounds(205, 10, 85, 21);
		panel_2.add(addButton);

		// set action listener for addButton
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					// create and set employee object from fields
					Employee employee = new Employee();
					employee.setEmployeeName(nameField.getText());
					employee.setEmployeePosition(positionField.getText());
					employee.setEmployeeSalary(Double.parseDouble(salaryField.getText()));
					employee.setAnnualLeave((int) annualLeaveField.getValue());
					employee.setSickLeave((int) sickLeaveField.getValue());
					employee.setGender((String) genderField.getSelectedItem());
					employee.setAccountNo(Integer.parseInt(accNoField.getText()));
					employee.setDateJoined(Date.valueOf(dateJoinedField.getText()));
					employee.setAge((int) ageField.getValue());
					employee.setPhoneNo(phoneNoField.getText());

					// create employee controller object
					EmployeeController employeeController = new EmployeeController();

					// insert employee
					employeeController.insertEmployee(employee);

				} catch (NullPointerException nullException) {
					new JOptionPane().showMessageDialog(null, "Please enter the required fields.");
				} catch (NumberFormatException numberException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for number value. Please try again");
				} catch (IllegalArgumentException dateException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for date value. Please try again");
				}

			}

		});

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 244, 486, 19);
		addEmployeeFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel leaveInformationLabel = new JLabel("Leave Information: ");
		leaveInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		leaveInformationLabel.setBounds(0, 0, 190, 19);
		panel_3.add(leaveInformationLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 263, 486, 40);
		addEmployeeFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JLabel sickLeaveLabel = new JLabel("Sick Leave (Days): ");
		sickLeaveLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		sickLeaveLabel.setBounds(0, 10, 130, 20);
		panel_4.add(sickLeaveLabel);

		sickLeaveField = new JSpinner();
		sickLeaveField.setBounds(140, 13, 82, 20);
		panel_4.add(sickLeaveField);

		JLabel annualLeaveLabel = new JLabel("Annual Leave (Days): ");
		annualLeaveLabel.setFont(new Font("Verdana", Font.PLAIN, 13));
		annualLeaveLabel.setBounds(243, 10, 152, 20);
		panel_4.add(annualLeaveLabel);

		annualLeaveField = new JSpinner();
		annualLeaveField.setBounds(394, 13, 82, 20);
		panel_4.add(annualLeaveField);

	}
}
