package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import controller.ClaimController;
import model.Claim;

public class ViewClaimList {

	private JFrame viewClaimListFrame;
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
					ViewClaimList window = new ViewClaimList();
					window.viewClaimListFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewClaimList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		viewClaimListFrame = new JFrame();
		viewClaimListFrame.setTitle("View Claim List");
		viewClaimListFrame.setBounds(100, 100, 520, 400);
		viewClaimListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewClaimListFrame.getContentPane().setLayout(null);

		JLabel claimListLabel = new JLabel("Claim List");
		claimListLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		claimListLabel.setBounds(200, 10, 94, 25);
		viewClaimListFrame.getContentPane().add(claimListLabel);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 25);
		viewClaimListFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 322, 486, 31);
		viewClaimListFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(391, 5, 85, 21);
		panel_1.add(goBackButton);

		// set action listener for go back button
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				viewClaimListFrame.dispose();
				new OptionUser().main(null);
			}

		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 486, 280);
		viewClaimListFrame.getContentPane().add(scrollPane);

		// set value for table model
		claimTable = new JTable();
		ClaimController claimController = new ClaimController();
		ArrayList<Claim> claimList = claimController.getClaimList();
		for (Claim claim : claimList) {
			int claimID = claim.getClaimID();
			int employeeID = claim.getEmployeeID();
			double amount = claim.getAmount();
			String status = claim.getStatus();
			Date date = claim.getDate();

			Object[] row = { claimID, employeeID, amount, status, date };
			model.addRow(row);
		}
		claimTable.setModel(model);
		claimTable.setFont(new Font("Verdana", Font.PLAIN, 10));
		scrollPane.setViewportView(claimTable);
	}
}
