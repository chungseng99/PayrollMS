package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OptionManagePayroll {

	private JFrame optionOfManagePayrollFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OptionManagePayroll window = new OptionManagePayroll();
					window.optionOfManagePayrollFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OptionManagePayroll() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		optionOfManagePayrollFrame = new JFrame();
		optionOfManagePayrollFrame.setTitle("Option of Manage Payroll");
		optionOfManagePayrollFrame.setBounds(100, 100, 520, 400);
		optionOfManagePayrollFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		optionOfManagePayrollFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 486, 60);
		optionOfManagePayrollFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel selectAnActionLabel = new JLabel("Select An Action");
		selectAnActionLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		selectAnActionLabel.setBounds(160, 22, 171, 28);
		panel.add(selectAnActionLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 80, 486, 203);
		optionOfManagePayrollFrame.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(2, 1));

		JButton generatePayrollButton = new JButton("Generate Payroll");
		generatePayrollButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_2.add(generatePayrollButton);

		// set action listener for generate payroll button
		generatePayrollButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionOfManagePayrollFrame.dispose();
				new GeneratePayroll().main(null);
			}

		});

		JButton deletePayrollButton = new JButton("Delete Payroll");
		deletePayrollButton.setFont(new Font("Verdana", Font.BOLD, 10));
		panel_2.add(deletePayrollButton);

		// set action listener for delete payroll button
		deletePayrollButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				optionOfManagePayrollFrame.dispose();
				new DeletePayroll().main(null);
			}

		});
	}

}
