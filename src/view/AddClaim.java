package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.ClaimController;
import model.Claim;

import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JComboBox;

public class AddClaim {

	private JFrame addClaimFrame;
	private JTextField employeeIDField;
	private JTextField amountField;
	private JTextField dateField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddClaim window = new AddClaim();
					window.addClaimFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddClaim() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addClaimFrame = new JFrame();
		addClaimFrame.setTitle("Add Claim");
		addClaimFrame.setBounds(100, 100, 520, 400);
		addClaimFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addClaimFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 47, 486, 24);
		addClaimFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel employeeInformationLabel = new JLabel("Employee Information: ");
		employeeInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		employeeInformationLabel.setBounds(0, 0, 205, 20);
		panel.add(employeeInformationLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 81, 486, 80);
		addClaimFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel employeeIDLabel = new JLabel("Employee ID: ");
		employeeIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		employeeIDLabel.setBounds(80, 33, 88, 13);
		panel_1.add(employeeIDLabel);

		employeeIDField = new JTextField();
		employeeIDField.setBounds(170, 30, 225, 19);
		panel_1.add(employeeIDField);
		employeeIDField.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 171, 486, 24);
		addClaimFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel claimInformationLabel = new JLabel("Claim Information: ");
		claimInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		claimInformationLabel.setBounds(0, 0, 178, 24);
		panel_2.add(claimInformationLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 205, 486, 80);
		addClaimFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JLabel amountLabel = new JLabel("Amount: ");
		amountLabel.setBounds(95, 0, 210, 80);
		amountLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		panel_3.add(amountLabel);

		amountField = new JTextField();
		amountField.setBounds(170, 31, 225, 19);
		panel_3.add(amountField);
		amountField.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 295, 486, 40);
		addClaimFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Verdana", Font.BOLD, 10));
		resetButton.setBounds(51, 10, 85, 21);
		panel_4.add(resetButton);

		// set action listener for reset button
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// reset field values
				employeeIDField.setText("");
				amountField.setText("");
				dateField.setText("");
			}

		});

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(351, 10, 85, 21);
		panel_4.add(goBackButton);

		// set action listener for goBackButton
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				addClaimFrame.dispose();
				new OptionApply().main(null);

			}

		});

		JButton applyButton = new JButton("Apply");
		applyButton.setFont(new Font("Verdana", Font.BOLD, 10));
		applyButton.setBounds(201, 10, 85, 21);
		panel_4.add(applyButton);

		// set action listener for applyButton
		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// create claim object
				Claim claim = new Claim();
				try {

					// set value for claim object from fields
					claim.setEmployeeID(Integer.parseInt(employeeIDField.getText()));
					claim.setAmount(Double.parseDouble(amountField.getText()));
					claim.setDate(Date.valueOf(dateField.getText()));
					claim.setStatus("Processing");

					// create claim controller object
					ClaimController claimController = new ClaimController();

					// insert claim into database
					claimController.insertClaim(claim);

				} catch (NumberFormatException numberException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for number value. Please try again");
				} catch (IllegalArgumentException dateException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for date value. Please try again");
				}

			}

		});

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 10, 486, 30);
		addClaimFrame.getContentPane().add(panel_5);
		panel_5.setLayout(null);

		JLabel dateLabel = new JLabel("Date: ");
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		dateLabel.setBounds(290, 10, 45, 13);
		panel_5.add(dateLabel);

		dateField = new JTextField();
		dateField.setBounds(335, 7, 141, 19);
		panel_5.add(dateField);
		dateField.setColumns(10);
	}

}
