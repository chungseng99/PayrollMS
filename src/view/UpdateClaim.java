package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JTextField;

import controller.ClaimController;
import exception.ItemNotAvailableForUpdateException;
import model.Claim;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class UpdateClaim {

	private JFrame updateClaimFrame;
	private JTextField claimIDField;
	private JTextField employeeIDField;
	private JTextField amountField;
	private JTextField dateField;
	private JComboBox statusField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClaim window = new UpdateClaim();
					window.updateClaimFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UpdateClaim() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		updateClaimFrame = new JFrame();
		updateClaimFrame.setTitle("Update Claim");
		updateClaimFrame.setBounds(100, 100, 520, 400);
		updateClaimFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateClaimFrame.getContentPane().setLayout(null);

		JLabel searchInformationLabel = new JLabel("Search Information: ");
		searchInformationLabel.setBounds(10, 10, 205, 25);
		searchInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		updateClaimFrame.getContentPane().add(searchInformationLabel);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 25);
		updateClaimFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 39, 486, 60);
		updateClaimFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel claimIDLabel = new JLabel("Claim ID: ");
		claimIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		claimIDLabel.setBounds(64, 23, 87, 13);
		panel_1.add(claimIDLabel);

		claimIDField = new JTextField();
		claimIDField.setBounds(153, 20, 180, 19);
		panel_1.add(claimIDField);
		claimIDField.setColumns(10);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Verdana", Font.BOLD, 10));
		searchButton.setBounds(343, 20, 85, 21);
		panel_1.add(searchButton);

		// set action listener for search button
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					// get claim ID from field
					int claimID = Integer.parseInt(claimIDField.getText());

					// create claim controller object
					ClaimController claimController = new ClaimController();

					// get claim from claim controller
					Claim claim = claimController.searchClaimByID(claimID);

					// set field to claim data
					employeeIDField.setText(Integer.toString(claim.getEmployeeID()));
					amountField.setText(Double.toString(claim.getAmount()));
					dateField.setText(claim.getDate().toString());
					statusField.setSelectedItem(claim.getStatus());

				} catch (NumberFormatException exception) {
					new JOptionPane().showMessageDialog(null, "Invalid input for number value. Please try again");
				} catch (ItemNotAvailableForUpdateException notForUpdateException) {
					// TODO Auto-generated catch block
					new JOptionPane().showMessageDialog(null, notForUpdateException.getMessage());
				} catch (NullPointerException nullException) {
					new JOptionPane().showMessageDialog(null, "Claim does not exist");
				}

			}

		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 105, 486, 25);
		updateClaimFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel claimInformationLabel = new JLabel("Claim Information: ");
		claimInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		claimInformationLabel.setBounds(0, 0, 170, 25);
		panel_2.add(claimInformationLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 293, 486, 60);
		updateClaimFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JButton resetButton = new JButton("Reset");
		resetButton.setFont(new Font("Verdana", Font.BOLD, 10));
		resetButton.setBounds(50, 10, 85, 21);
		panel_3.add(resetButton);

		// set action listener for reset button
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// reset fields
				claimIDField.setText("");
				employeeIDField.setText("");
				amountField.setText("");
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
				updateClaimFrame.dispose();
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
				// TODO Auto-generated method stub

				try {

					// create claim controller object
					ClaimController claimController = new ClaimController();

					// create and set claim object using field
					Claim claim = new Claim();
					claim.setClaimID(Integer.parseInt(claimIDField.getText()));
					claim.setEmployeeID(Integer.parseInt(employeeIDField.getText()));
					claim.setAmount(Double.parseDouble(amountField.getText()));
					claim.setDate(Date.valueOf(dateField.getText()));
					claim.setStatus((String) statusField.getSelectedItem());

					// update claim
					claimController.updateClaim(claim);

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
		panel_4.setBounds(10, 135, 486, 154);
		updateClaimFrame.getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JLabel employeeIDLabel = new JLabel("Employee ID: ");
		employeeIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		employeeIDLabel.setBounds(10, 0, 222, 77);
		panel_4.add(employeeIDLabel);

		JLabel dateLabel = new JLabel("Date: ");
		dateLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		dateLabel.setBounds(254, 0, 222, 77);
		panel_4.add(dateLabel);

		JLabel amountLabel = new JLabel("Amount: ");
		amountLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		amountLabel.setBounds(10, 77, 222, 77);
		panel_4.add(amountLabel);

		JLabel statusLabel = new JLabel("Status: ");
		statusLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		statusLabel.setBounds(254, 77, 222, 77);
		panel_4.add(statusLabel);

		employeeIDField = new JTextField();
		employeeIDField.setBounds(100, 29, 130, 19);
		panel_4.add(employeeIDField);
		employeeIDField.setColumns(10);

		amountField = new JTextField();
		amountField.setBounds(100, 106, 130, 19);
		panel_4.add(amountField);
		amountField.setColumns(10);

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
