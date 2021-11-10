package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.PayrollController;
import model.Payroll;

public class ViewPayrollList {

	private JFrame viewPayrollListFrame;
	private JTable payrollTable;
	private String[] cols = { "Payroll ID", "Employee ID", "Total Addition", "Total Deduction", "Total Amount",
			"Date" };
	private String[][] data = {};
	private DefaultTableModel model = new DefaultTableModel(data, cols);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPayrollList window = new ViewPayrollList();
					window.viewPayrollListFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewPayrollList() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		viewPayrollListFrame = new JFrame();
		viewPayrollListFrame.setTitle("View Payroll List");
		viewPayrollListFrame.setBounds(100, 100, 640, 400);
		viewPayrollListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewPayrollListFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 606, 25);
		viewPayrollListFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel payrollListLabel = new JLabel("Payroll List");
		payrollListLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		payrollListLabel.setBounds(260, 0, 97, 23);
		panel.add(payrollListLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 311, 606, 42);
		viewPayrollListFrame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.setFont(new Font("Verdana", Font.BOLD, 10));
		goBackButton.setBounds(511, 10, 85, 21);
		panel_1.add(goBackButton);

		// set action listener for go back button
		goBackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// dispose current frame and open new frame
				viewPayrollListFrame.dispose();
				new OptionUser().main(null);

			}

		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 606, 269);
		viewPayrollListFrame.getContentPane().add(scrollPane);

		payrollTable = new JTable();
		// create payroll controller object
		PayrollController payrollController = new PayrollController();

		// get payroll list from payroll controller
		ArrayList<Payroll> payrollList = payrollController.getListOfPayroll();

		// set value for table model
		for (Payroll payroll : payrollList) {

			int payrollID = payroll.getPayrollID();
			int employeeID = payroll.getEmployeeID();
			double totalClaim = payroll.getTotalAddition();
			double totalDeduction = payroll.getTotalDeduction();
			double totalAmount = payroll.getTotalAmount();
			Date date = payroll.getDate();

			Object[] row = { payrollID, employeeID, totalClaim, totalDeduction, totalAmount, date };
			model.addRow(row);

		}
		payrollTable.setModel(model);
		payrollTable.setFont(new Font("Verdana", Font.PLAIN, 10));
		scrollPane.setViewportView(payrollTable);
	}
}
