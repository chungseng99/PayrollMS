package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class OptionApply {

	private JFrame optionOfApplyFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionApply window = new OptionApply();
					window.optionOfApplyFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OptionApply() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		optionOfApplyFrame = new JFrame();
		optionOfApplyFrame.setTitle("Option of Apply");
		optionOfApplyFrame.setBounds(100, 100, 520, 400);
		optionOfApplyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		optionOfApplyFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 25, 486, 70);
		optionOfApplyFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel selectAnActionLabel = new JLabel("Select An Action");
		selectAnActionLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		selectAnActionLabel.setBounds(162, 25, 172, 20);
		panel.add(selectAnActionLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 105, 486, 230);
		optionOfApplyFrame.getContentPane().add(panel_1);
		panel_1.setLayout(new GridLayout(4, 1));

		JButton addClaimButton = new JButton("Add Claim");
		addClaimButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(addClaimButton);

		// set action listener for add claim button
		addClaimButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionOfApplyFrame.dispose();
				new AddClaim().main(null);

			}

		});

		JButton addLeaveButton = new JButton("Add Leave");
		addLeaveButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(addLeaveButton);

		// set action listener for add leave button
		addLeaveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionOfApplyFrame.dispose();
				new AddLeave().main(null);

			}

		});

		JButton viewClaimStatusButton = new JButton("View Claim Status");
		viewClaimStatusButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(viewClaimStatusButton);

		// set action listener for view claim status button
		viewClaimStatusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionOfApplyFrame.dispose();
				new ViewClaimStatus().main(null);

			}

		});

		JButton viewLeaveStatusButton = new JButton("View Leave Status");
		viewLeaveStatusButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_1.add(viewLeaveStatusButton);

		// set action listener for view leave status button
		viewLeaveStatusButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionOfApplyFrame.dispose();
				new ViewLeaveStatus().main(null);

			}

		});
	}

}
