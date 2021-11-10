package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.EmployeeController;
import exception.ItemNotAvailableForUpdateException;
import model.Employee;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;

public class UpdateEmployee {

	private JFrame updateEmployeeFrame;
	private JTextField employeeIDField;
	private JTextField employeeNameField;
	private JTextField accNoField;
	private JTextField positionField;
	private JTextField dateJoinedField;
	private JTextField phoneNoField;
	private JTextField salaryField;
	private JSpinner sickLeaveField;
	private JSpinner annualLeaveField;
	private JComboBox genderField;
	private JSpinner ageField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateEmployee window = new UpdateEmployee();
					window.updateEmployeeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateEmployee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		updateEmployeeFrame = new JFrame();
		updateEmployeeFrame.setTitle("Update Employee");
		updateEmployeeFrame.setBounds(100, 100, 530, 400);
		updateEmployeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateEmployeeFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 496, 21);
		updateEmployeeFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel employeeInformationLabel = new JLabel("Employee Information: ");
		employeeInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		employeeInformationLabel.setBounds(0, 0, 205, 20);
		panel.add(employeeInformationLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 30, 496, 40);
		updateEmployeeFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel employeeIDLabel = new JLabel("Employee ID: ");
		employeeIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		employeeIDLabel.setBounds(70, 14, 84, 13);
		panel_1.add(employeeIDLabel);

		employeeIDField = new JTextField();
		employeeIDField.setBounds(164, 11, 165, 19);
		panel_1.add(employeeIDField);
		employeeIDField.setColumns(10);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Verdana", Font.BOLD, 10));
		searchButton.setBounds(339, 10, 85, 21);
		panel_1.add(searchButton);

		// set action listener for search button
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					// get employee ID from field
					int employeeID = Integer.parseInt(employeeIDField.getText());

					// create employee controller object and get employee
					EmployeeController employeeController = new EmployeeController();
					Employee employee = employeeController.searchByEmployeeID(employeeID);

					// set employee object with field
					employeeNameField.setText(employee.getEmployeeName());
					accNoField.setText(Integer.toString(employee.getAccountNo()));
					positionField.setText(employee.getEmployeePosition());
					dateJoinedField.setText(employee.getDateJoined().toString());
					phoneNoField.setText(employee.getPhoneNo());
					salaryField.setText(String.valueOf(employee.getEmployeeSalary()));
					sickLeaveField.setValue(employee.getSickLeave());
					annualLeaveField.setValue(employee.getAnnualLeave());
					genderField.setSelectedItem(employee.getGender());
					ageField.setValue(employee.getAge());

				} catch (NumberFormatException exception) {
					new JOptionPane().showMessageDialog(null, "Invalid input for number value. Please try again");
				} catch (NullPointerException nullException) {
					new JOptionPane().showMessageDialog(null, "Employee does not exist");
				}

			}

		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 68, 496, 180);
		updateEmployeeFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel nameLabel = new JLabel("Name: ");
		nameLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		nameLabel.setBounds(0, 0, 248, 45);
		panel_2.add(nameLabel);

		JLabel phoneNoLabel = new JLabel("Phone No: ");
		phoneNoLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		phoneNoLabel.setBounds(248, 0, 248, 45);
		panel_2.add(phoneNoLabel);

		JLabel genderLabel = new JLabel("Gender: ");
		genderLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		genderLabel.setBounds(0, 45, 248, 45);
		panel_2.add(genderLabel);

		JLabel ageLabel = new JLabel("Age: ");
		ageLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		ageLabel.setBounds(248, 45, 248, 45);
		panel_2.add(ageLabel);

		JLabel accNoLabel = new JLabel("Acc No: ");
		accNoLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		accNoLabel.setBounds(0, 90, 248, 45);
		panel_2.add(accNoLabel);

		JLabel dateJoinedLabel = new JLabel("Date Join: ");
		dateJoinedLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		dateJoinedLabel.setBounds(248, 90, 248, 45);
		panel_2.add(dateJoinedLabel);

		JLabel positionLabel = new JLabel("Position: ");
		positionLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		positionLabel.setBounds(0, 135, 248, 45);
		panel_2.add(positionLabel);

		JLabel salaryLabel = new JLabel("Salary: ");
		salaryLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		salaryLabel.setBounds(248, 135, 248, 45);
		panel_2.add(salaryLabel);

		employeeNameField = new JTextField();
		employeeNameField.setBounds(60, 13, 165, 19);
		panel_2.add(employeeNameField);
		employeeNameField.setColumns(10);

		String[] choices = { "Male", "Female" };
		genderField = new JComboBox(choices);
		genderField.setBounds(60, 57, 165, 21);
		genderField.setSelectedIndex(0);
		panel_2.add(genderField);

		accNoField = new JTextField();
		accNoField.setBounds(60, 103, 165, 19);
		panel_2.add(accNoField);
		accNoField.setColumns(10);

		positionField = new JTextField();
		positionField.setBounds(60, 148, 165, 19);
		panel_2.add(positionField);
		positionField.setColumns(10);

		dateJoinedField = new JTextField();
		dateJoinedField.setBounds(320, 103, 166, 19);
		panel_2.add(dateJoinedField);
		dateJoinedField.setColumns(10);

		phoneNoField = new JTextField();
		phoneNoField.setBounds(320, 13, 166, 19);
		panel_2.add(phoneNoField);
		phoneNoField.setColumns(10);

		ageField = new JSpinner();
		ageField.setBounds(320, 55, 166, 20);
		panel_2.add(ageField);

		salaryField = new JTextField();
		salaryField.setBounds(320, 151, 166, 19);
		panel_2.add(salaryField);
		salaryField.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 247, 496, 20);
		updateEmployeeFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Leave Information: ");
		lblNewLabel_2.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel_2.setBounds(0, 0, 172, 20);
		panel_3.add(lblNewLabel_2);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 267, 496, 48);
		updateEmployeeFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JLabel sickLeaveLabel = new JLabel("Sick Leave (Days): ");
		sickLeaveLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		sickLeaveLabel.setBounds(0, 0, 248, 48);
		panel_4.add(sickLeaveLabel);

		JLabel annualLeaveLabel = new JLabel("Annual Leave (Days): ");
		annualLeaveLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		annualLeaveLabel.setBounds(248, 0, 248, 48);
		panel_4.add(annualLeaveLabel);

		sickLeaveField = new JSpinner();
		sickLeaveField.setBounds(115, 15, 110, 20);
		panel_4.add(sickLeaveField);

		annualLeaveField = new JSpinner();
		annualLeaveField.setBounds(376, 15, 110, 20);
		panel_4.add(annualLeaveField);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 314, 496, 39);
		updateEmployeeFrame.getContentPane().add(panel_5);
		panel_5.setLayout(null);

		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Verdana", Font.BOLD, 10));
		resetButton.setBounds(50, 10, 85, 21);
		panel_5.add(resetButton);

		// set action listener for reset button
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// reset fields
				employeeIDField.setText("");
				employeeNameField.setText("");
				accNoField.setText("");
				positionField.setText("");
				dateJoinedField.setText("");
				phoneNoField.setText("");
				salaryField.setText("");
				sickLeaveField.setValue(0);
				annualLeaveField.setValue(0);
				genderField.setSelectedIndex(0);
				ageField.setValue(0);

			}

		});

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(350, 10, 85, 21);
		panel_5.add(goBackButton);

		// set action listener for go back button
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				updateEmployeeFrame.dispose();
				new OptionUser().main(null);

			}

		});

		JButton updateButton = new JButton("Update");
		updateButton.setFont(new Font("Verdana", Font.BOLD, 10));
		updateButton.setBounds(200, 10, 85, 21);
		panel_5.add(updateButton);

		// set action listener for update button
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					// create and set employee object with fields
					Employee employee = new Employee();
					employee.setEmployeeID(Integer.parseInt(employeeIDField.getText()));
					employee.setEmployeeName(employeeNameField.getText());
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

					// update employee
					employeeController.updateEmployee(employee);

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
