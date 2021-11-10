package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionUser {

	private JFrame optionScreenFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionUser window = new OptionUser();
					window.optionScreenFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OptionUser() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		optionScreenFrame = new JFrame();
		optionScreenFrame.setTitle("Option Screen");
		optionScreenFrame.setBounds(100, 100, 520, 400);
		optionScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		optionScreenFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 60);
		optionScreenFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel selectAnActionLabel = new JLabel("Select An Action");
		selectAnActionLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		selectAnActionLabel.setBounds(163, 31, 172, 19);
		panel.add(selectAnActionLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 90, 486, 243);
		optionScreenFrame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(4, 2));

		JButton manageEmployeeButton = new JButton("Manage Employee");
		manageEmployeeButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(manageEmployeeButton);

		// set action listener for manage employee button
		manageEmployeeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionScreenFrame.dispose();
				new OptionManageEmployee().main(null);

			}

		});

		JButton manageClaimButton = new JButton("Manage Claim");
		manageClaimButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(manageClaimButton);

		// set action listener for manage claim button
		manageClaimButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionScreenFrame.dispose();
				new UpdateClaim().main(null);
			}

		});

		JButton manageLeaveApplicationButton = new JButton("Manage Leave Application");
		manageLeaveApplicationButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(manageLeaveApplicationButton);

		// set action listener for manage leave application button
		manageLeaveApplicationButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionScreenFrame.dispose();
				new UpdateLeave().main(null);
			}

		});

		JButton managePayrollButton = new JButton("Manage Payroll");
		managePayrollButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(managePayrollButton);

		// set action listener for manage payroll button
		managePayrollButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionScreenFrame.dispose();
				new OptionManagePayroll().main(null);

			}

		});

		JButton viewEmployeeListButton = new JButton("View Employee List");
		viewEmployeeListButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(viewEmployeeListButton);

		// set action listener for view employee list button
		viewEmployeeListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionScreenFrame.dispose();
				new ViewEmployeeList().main(null);
			}

		});

		JButton viewClaimListButton = new JButton("View Claim List");
		viewClaimListButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(viewClaimListButton);

		// set action listener for view claim list button
		viewClaimListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionScreenFrame.dispose();
				new ViewClaimList().main(null);
			}

		});

		JButton viewLeaveListButton = new JButton("View Leave List");
		viewLeaveListButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(viewLeaveListButton);

		// set action listener for view leave list button
		viewLeaveListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionScreenFrame.dispose();
				new ViewLeaveList().main(null);

			}

		});

		JButton viewPayrollListButton = new JButton("View Payroll List");
		viewPayrollListButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(viewPayrollListButton);

		// set action listener for view payroll list button
		viewPayrollListButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionScreenFrame.dispose();
				new ViewPayrollList().main(null);

			}

		});
	}

}
