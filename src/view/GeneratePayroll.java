package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controller.PayrollController;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class GeneratePayroll {

	private JFrame generatePayrollFrame;
	private JTextField employeeIDField;
	private JSpinner overTimeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneratePayroll window = new GeneratePayroll();
					window.generatePayrollFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GeneratePayroll() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		generatePayrollFrame = new JFrame();
		generatePayrollFrame.setTitle("Generate Payroll");
		generatePayrollFrame.setBounds(100, 100, 520, 400);
		generatePayrollFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		generatePayrollFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 25);
		generatePayrollFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel employeeInformationLabel = new JLabel("Employee Information: ");
		employeeInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		employeeInformationLabel.setBounds(0, 0, 205, 23);
		panel.add(employeeInformationLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 45, 486, 60);
		generatePayrollFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel employeeIDLabel = new JLabel("Employee ID: ");
		employeeIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		employeeIDLabel.setBounds(80, 21, 88, 13);
		panel_1.add(employeeIDLabel);

		employeeIDField = new JTextField();
		employeeIDField.setBounds(175, 18, 200, 19);
		panel_1.add(employeeIDField);
		employeeIDField.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 114, 486, 25);
		generatePayrollFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel workingInformationLabel = new JLabel("Working Hour Information: ");
		workingInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		workingInformationLabel.setBounds(0, 0, 241, 23);
		panel_2.add(workingInformationLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 149, 486, 60);
		generatePayrollFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel overtimeHourLabel = new JLabel("Overtime Hour: ");
		overtimeHourLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		overtimeHourLabel.setBounds(78, 23, 98, 13);
		panel_3.add(overtimeHourLabel);

		overTimeField = new JSpinner();
		overTimeField.setBounds(175, 16, 200, 20);
		panel_3.add(overTimeField);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 217, 486, 60);
		generatePayrollFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Verdana", Font.BOLD, 10));
		resetButton.setBounds(50, 15, 85, 21);
		panel_4.add(resetButton);

		// set action listener for reset button
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// reset fields
				employeeIDField.setText("");
				overTimeField.setValue(0);

			}

		});

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(350, 15, 85, 21);
		panel_4.add(goBackButton);

		// set action listener for go back button
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				generatePayrollFrame.dispose();
				new OptionUser().main(null);

			}

		});

		JButton generateButton = new JButton("Generate");
		generateButton.setFont(new Font("Verdana", Font.BOLD, 10));
		generateButton.setBounds(200, 15, 94, 21);
		panel_4.add(generateButton);

		// set action listener for generate button
		generateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					// get employee ID and overtime hour from field
					int employeeID = Integer.parseInt(employeeIDField.getText());
					int overTimeHour = (int) overTimeField.getValue();

					// create payroll controller object
					PayrollController payrollController = new PayrollController();

					// generate payroll
					payrollController.generatePayroll(employeeID, overTimeHour);

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
