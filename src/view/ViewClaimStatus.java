package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.ClaimController;
import model.Claim;

public class ViewClaimStatus {

	private JFrame viewClaimStatusFrame;
	private JTextField employeeIDField;
	private JTable claimTable;
	private String[] cols = { "Claim ID", "Employee ID", "Amount", "Date", "Status" };
	private String[][] data = {};
	private DefaultTableModel model = new DefaultTableModel(data, cols);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewClaimStatus window = new ViewClaimStatus();
					window.viewClaimStatusFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewClaimStatus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		viewClaimStatusFrame = new JFrame();
		viewClaimStatusFrame.setTitle("View Claim Status");
		viewClaimStatusFrame.setBounds(100, 100, 520, 400);
		viewClaimStatusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewClaimStatusFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 25);
		viewClaimStatusFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel employeeInformationLabel = new JLabel("Employee Information: ");
		employeeInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		employeeInformationLabel.setBounds(0, 0, 205, 25);
		panel.add(employeeInformationLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 38, 486, 63);
		viewClaimStatusFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel employeeIDLabel = new JLabel("Employee ID: ");
		employeeIDLabel.setFont(new Font("Verdana", Font.PLAIN, 10));
		employeeIDLabel.setBounds(40, 25, 90, 13);
		panel_1.add(employeeIDLabel);

		employeeIDField = new JTextField();
		employeeIDField.setBounds(140, 22, 190, 19);
		panel_1.add(employeeIDField);
		employeeIDField.setColumns(10);

		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Verdana", Font.BOLD, 10));
		searchButton.setBounds(351, 21, 85, 21);
		panel_1.add(searchButton);

		// set action listener for search button
		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {

					// get employee ID from field
					int employeeIDFieldValue = Integer.parseInt(employeeIDField.getText());

					// create claim controller object
					ClaimController claimController = new ClaimController();

					// get claim list from claim controller
					ArrayList<Claim> claimList = claimController.getClaimListByEmployeeID(employeeIDFieldValue);

					model = new DefaultTableModel(data, cols);

					// set value for table model
					for (Claim claim : claimList) {

						int claimID = claim.getClaimID();
						int employeeID = claim.getEmployeeID();
						double amount = claim.getAmount();
						String status = claim.getStatus();
						Date date = claim.getDate();

						Object[] row = { claimID, employeeID, amount, date, status };
						model.addRow(row);

					}

					// set model for table
					claimTable.setModel(model);

				} catch (NullPointerException nullException) {
					new JOptionPane().showMessageDialog(null, "Please enter the required fields.");
				} catch (NumberFormatException numberException) {
					new JOptionPane().showMessageDialog(null, "Invalid input for number value. Please try again");
				}

			}

		});

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 105, 486, 25);
		viewClaimStatusFrame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JLabel claimInformationLabel = new JLabel("Claim Information: ");
		claimInformationLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		claimInformationLabel.setBounds(0, 0, 170, 25);
		panel_2.add(claimInformationLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 290, 486, 63);
		viewClaimStatusFrame.getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(391, 21, 85, 21);
		panel_3.add(goBackButton);

		// set action listener for go back button
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				viewClaimStatusFrame.dispose();
				new OptionApply().main(null);
			}

		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 132, 486, 153);
		viewClaimStatusFrame.getContentPane().add(scrollPane);

		claimTable = new JTable();
		claimTable.setModel(model);
		claimTable.setFont(new Font("Verdana", Font.PLAIN, 10));
		scrollPane.setViewportView(claimTable);
	}
}
