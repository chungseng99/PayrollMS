package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import controller.PayrollController;

public class DeletePayroll {

	private JFrame deletePayrollFrame;
	private JTextField payrollIDField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeletePayroll window = new DeletePayroll();
					window.deletePayrollFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeletePayroll() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		deletePayrollFrame = new JFrame();
		deletePayrollFrame.setTitle("Delete Payroll");
		deletePayrollFrame.setBounds(100, 100, 520, 400);
		deletePayrollFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deletePayrollFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 82, 486, 71);
		deletePayrollFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel payrollIDLabel = new JLabel("Payroll ID: ");
		payrollIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		payrollIDLabel.setBounds(85, 29, 84, 13);
		panel.add(payrollIDLabel);

		payrollIDField = new JTextField();
		payrollIDField.setBounds(179, 26, 211, 19);
		panel.add(payrollIDField);
		payrollIDField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 152, 486, 77);
		deletePayrollFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Verdana", Font.BOLD, 10));
		resetButton.setBounds(62, 40, 85, 21);
		panel_1.add(resetButton);

		// set action listener for resetButton
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// reset field
				payrollIDField.setText("");

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
				deletePayrollFrame.dispose();
				new OptionUser().main(null);

			}

		});

		JButton deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Verdana", Font.BOLD, 10));
		deleteButton.setBounds(201, 40, 85, 21);
		panel_1.add(deleteButton);

		// set action listener for delete Button
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					// get payroll ID from field
					int payrollID = Integer.parseInt(payrollIDField.getText());

					// create payroll controller object
					PayrollController payrollController = new PayrollController();

					// delete payroll
					payrollController.deletePayrollByID(payrollID);

				} catch (NullPointerException nullException) {
					new JOptionPane().showMessageDialog(null, "Please enter the required fields.");
				} catch (NumberFormatException numberException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for number value. Please try again");
				}

			}

		});

	}

}
