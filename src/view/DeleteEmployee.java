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

import controller.EmployeeController;

import javax.swing.JButton;

public class DeleteEmployee {

	private JFrame deleteEmployeeFrame;
	private JTextField employeeIDField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteEmployee window = new DeleteEmployee();
					window.deleteEmployeeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteEmployee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		deleteEmployeeFrame = new JFrame();
		deleteEmployeeFrame.setTitle("Delete Employee");
		deleteEmployeeFrame.setBounds(100, 100, 520, 400);
		deleteEmployeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deleteEmployeeFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 82, 486, 71);
		deleteEmployeeFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel employeeIDLabel = new JLabel("Employee ID: ");
		employeeIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		employeeIDLabel.setBounds(85, 29, 84, 13);
		panel.add(employeeIDLabel);

		employeeIDField = new JTextField();
		employeeIDField.setBounds(179, 26, 211, 19);
		panel.add(employeeIDField);
		employeeIDField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 152, 486, 77);
		deleteEmployeeFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Verdana", Font.BOLD, 10));
		resetButton.setBounds(62, 40, 85, 21);
		panel_1.add(resetButton);

		// set action listener for resetButton
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// reset fields
				employeeIDField.setText("");
			}

		});

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(341, 40, 85, 21);
		panel_1.add(goBackButton);

		// set action listener for goBackButton
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				deleteEmployeeFrame.dispose();
				new OptionUser().main(null);

			}

		});

		JButton deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Verdana", Font.BOLD, 10));
		deleteButton.setBounds(201, 40, 85, 21);
		panel_1.add(deleteButton);

		// set action listener for deleteButton
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					// get employee ID from field
					int employeeID = Integer.parseInt(employeeIDField.getText());

					// create employee controller object
					EmployeeController employeeController = new EmployeeController();

					// delete employee
					employeeController.deleteEmployeeByID(employeeID);

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
