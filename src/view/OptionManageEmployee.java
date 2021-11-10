package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OptionManageEmployee {

	private JFrame optionOfManageEmployeeFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionManageEmployee window = new OptionManageEmployee();
					window.optionOfManageEmployeeFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OptionManageEmployee() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		optionOfManageEmployeeFrame = new JFrame();
		optionOfManageEmployeeFrame.setTitle("Option of Manage Employee");
		optionOfManageEmployeeFrame.setBounds(100, 100, 520, 400);
		optionOfManageEmployeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		optionOfManageEmployeeFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 72);
		optionOfManageEmployeeFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel selectAnActionLabel = new JLabel("Select An Action");
		selectAnActionLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		selectAnActionLabel.setBounds(160, 24, 172, 26);
		panel.add(selectAnActionLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 92, 486, 250);
		optionOfManageEmployeeFrame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(3, 1));

		JButton addEmployeeButton = new JButton("Add Employee");
		addEmployeeButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(addEmployeeButton);

		// set action listener for add employee button
		addEmployeeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionOfManageEmployeeFrame.dispose();
				new AddEmployee().main(null);
			}

		});

		JButton updateEmployeeButton = new JButton("Update Employee");
		updateEmployeeButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(updateEmployeeButton);

		// set action listener for update employee button
		updateEmployeeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionOfManageEmployeeFrame.dispose();
				new UpdateEmployee().main(null);
			}

		});

		JButton deleteEmployeeButton = new JButton("Delete Employee");
		deleteEmployeeButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(deleteEmployeeButton);

		// set action listener for delete employee button
		deleteEmployeeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionOfManageEmployeeFrame.dispose();
				new DeleteEmployee().main(null);

			}

		});

	}

}
