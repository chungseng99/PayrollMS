package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import model.Claim;
import model.Employee;
import model.Payroll;

/**
 * This is class of Excel Controller
 * 
 * @author Group L
 *
 */
public class ExcelController {

	/**
	 * Constructor of this controller class
	 */
	public ExcelController() {
	}

	/**
	 * Function to generate excel payslip
	 * 
	 * @param payroll
	 * @param claimList
	 * @param overtimeHours
	 * @param totalLeaveDeduction
	 */
	public void generateExcelPayslip(Payroll payroll, ArrayList<Claim> claimList, int overtimeHours,
			int totalLeaveDeduction) {

		// set file path
		String filePath = "Payroll/" + Integer.toString(payroll.getPayrollID()) + ".xlsx";

		// create employee controller object
		EmployeeController employeeController = new EmployeeController();

		// get employee from employeController
		Employee employee = employeeController.searchByEmployeeID(payroll.getEmployeeID());

		// Create blank workbook
		XSSFWorkbook workBook = new XSSFWorkbook();

		// Create a blank sheet
		XSSFSheet spreadsheet = workBook.createSheet(" Payroll Info ");

		// Create row object
		Row row;
		Cell cell;

		// initialize row count, cell count, max cell count
		int rowCount = 0;
		int cellCount = 0;
		int maxCellCount = 5;

		// initialize epf, sosco and totalDeduction
		double epf = 0;
		double sosco = 0;
		double totalDeduction = 0;

		// create and set style for bold cell style
		CellStyle boldCellStyle = workBook.createCellStyle();
		Font boldFont = workBook.createFont();
		boldFont.setBold(true);
		boldCellStyle.setFont(boldFont);

		// create and set style for align to left style
		CellStyle alignCellStyle = workBook.createCellStyle();
		alignCellStyle.setAlignment(HorizontalAlignment.LEFT);

		// create and set style for section label style
		CellStyle sectionLabelStyle = workBook.createCellStyle();
		sectionLabelStyle.setFont(boldFont);
		sectionLabelStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		sectionLabelStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

		// create and set style for net pay style
		CellStyle netPayStyle = workBook.createCellStyle();
		Font netPayFont = workBook.createFont();
		netPayFont.setBold(false);
		netPayStyle.setFont(netPayFont);
		netPayStyle.setBorderTop(BorderStyle.THIN);
		netPayStyle.setBorderBottom(BorderStyle.THIN);

		// create and set style for net pay bold style
		CellStyle netPayStyleBold = workBook.createCellStyle();
		Font netPayFontBold = workBook.createFont();
		netPayFontBold.setBold(true);
		netPayStyleBold.setFont(netPayFontBold);
		netPayStyleBold.setBorderTop(BorderStyle.THIN);
		netPayStyleBold.setBorderBottom(BorderStyle.THIN);

		// create row
		row = spreadsheet.createRow(rowCount++);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue("Employee ID:");
		cell.setCellStyle(boldCellStyle);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue(employee.getEmployeeID());
		cell.setCellStyle(alignCellStyle);

		// create row and reset cell count
		row = spreadsheet.createRow(rowCount++);
		cellCount = 0;

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue("Name:");
		cell.setCellStyle(boldCellStyle);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue(employee.getEmployeeName());
		cell.setCellStyle(alignCellStyle);

		// create empty cell
		cell = row.createCell(cellCount++);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue("Date Joined:");
		cell.setCellStyle(boldCellStyle);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue(employee.getDateJoined().toString());
		cell.setCellStyle(alignCellStyle);

		// create row and reset cell count
		row = spreadsheet.createRow(rowCount++);
		cellCount = 0;

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue("Position:");
		cell.setCellStyle(boldCellStyle);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue(employee.getEmployeePosition());
		cell.setCellStyle(alignCellStyle);

		// create empty cell
		cell = row.createCell(cellCount++);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue("Account No:");
		cell.setCellStyle(boldCellStyle);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue(Integer.toString(employee.getAccountNo()));
		cell.setCellStyle(alignCellStyle);

		// create row and reset cell count
		row = spreadsheet.createRow(rowCount++);
		cellCount = 0;

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue("Annual Leave:");
		cell.setCellStyle(boldCellStyle);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue(employee.getAnnualLeave());
		cell.setCellStyle(alignCellStyle);

		// create empty cell
		cell = row.createCell(cellCount++);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue("Sick Leave:");
		cell.setCellStyle(boldCellStyle);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue(Integer.toString(employee.getSickLeave()));
		cell.setCellStyle(alignCellStyle);

		// create row and reset cell count
		row = spreadsheet.createRow(rowCount++);
		cellCount = 0;

		// create empty row
		row = spreadsheet.createRow(rowCount++);

		// create row
		row = spreadsheet.createRow(rowCount++);

		// create section label cell that is merged
		cell = row.createCell(cellCount);
		cell.setCellStyle(sectionLabelStyle);
		cell.setCellValue("Salary Information");
		spreadsheet.addMergedRegion(new CellRangeAddress(rowCount - 1, rowCount - 1, cellCount, maxCellCount - 1));

		// create row
		row = spreadsheet.createRow(rowCount++);

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue("Basic Salary:");
		cell.setCellStyle(boldCellStyle);

		// create cell until last cell in format
		while (cellCount < maxCellCount) {
			cell = row.createCell(cellCount++);
		}
		// set cell value
		cell.setCellValue(employee.getEmployeeSalary());

		// reset cell count
		cellCount = 0;

		// create empty row
		row = spreadsheet.createRow(rowCount++);

		// create row
		row = spreadsheet.createRow(rowCount++);

		// create section label cell that is merged
		cell = row.createCell(cellCount);
		cell.setCellStyle(sectionLabelStyle);
		cell.setCellValue("Addition Information");
		spreadsheet.addMergedRegion(new CellRangeAddress(rowCount - 1, rowCount - 1, cellCount, maxCellCount - 1));

		// create claim cell for every claim in claim list
		for (Claim claim : claimList) {

			// set cell value
			String cellValue = "Claim #" + Integer.toString(claim.getClaimID());

			// create row and set cell value
			row = spreadsheet.createRow(rowCount++);
			cell = row.createCell(cellCount++);
			cell.setCellValue(cellValue);
			cell.setCellStyle(boldCellStyle);
			while (cellCount < maxCellCount) {
				cell = row.createCell(cellCount++);
			}
			cell.setCellValue(claim.getAmount());
			cellCount = 0;

		}

		// if overtime hour is greater than 0
		if (overtimeHours > 0) {

			// set cell value
			String cellValue = "Overtime Hour (" + Integer.toString(overtimeHours) + ")";

			// create row and set cell value
			row = spreadsheet.createRow(rowCount++);
			cell = row.createCell(cellCount++);
			cell.setCellValue(cellValue);
			cell.setCellStyle(boldCellStyle);
			while (cellCount < maxCellCount) {
				cell = row.createCell(cellCount++);
			}

			// calculate hour pay and total OT Pay
			double hourPay = employee.getEmployeeSalary() / 168;
			double totalOTPay = Math.round(hourPay * 1.5 * overtimeHours * 100.0) / 100.0;

			// set cell value
			cell.setCellValue(totalOTPay);
			cellCount = 0;

		}

		// create empty row
		row = spreadsheet.createRow(rowCount++);

		// create row
		row = spreadsheet.createRow(rowCount++);

		// create section label cell that is merged
		cell = row.createCell(cellCount);
		cell.setCellStyle(sectionLabelStyle);
		cell.setCellValue("Deduction Information");
		spreadsheet.addMergedRegion(new CellRangeAddress(rowCount - 1, rowCount - 1, cellCount, maxCellCount - 1));

		// if total leave deduction is greater than 0
		if (totalLeaveDeduction > 0) {

			// set cell value
			String cellValue = "Unpaid Leave (" + Integer.toString(totalLeaveDeduction) + ")";

			// create row and reset cell count
			row = spreadsheet.createRow(rowCount++);
			cellCount = 0;

			// create and set cell
			cell = row.createCell(cellCount++);
			cell.setCellValue(cellValue);
			cell.setCellStyle(boldCellStyle);

			// create cell until last cell of format
			while (cellCount < maxCellCount) {
				cell = row.createCell(cellCount++);
			}

			// set cell value
			double leaveDeduction = totalLeaveDeduction * 50;
			totalDeduction += leaveDeduction;
			cell.setCellValue(leaveDeduction);
			cellCount = 0;

		}

		// set cell value
		String cellValue = "EPF";

		// create row and reset cell count
		row = spreadsheet.createRow(rowCount++);
		cellCount = 0;

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue(cellValue);
		cell.setCellStyle(boldCellStyle);

		// create cell until last cell of format
		while (cellCount < maxCellCount) {
			cell = row.createCell(cellCount++);
		}

		// set cell value
		epf = Math.round(employee.getEmployeeSalary() * 0.08 * 100.0) / 100.0;
		totalDeduction += epf;
		cell.setCellValue(epf);
		cellCount = 0;

		// set cell value
		cellValue = "SOSCO";

		// create row and reset cell count
		row = spreadsheet.createRow(rowCount++);
		cellCount = 0;

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue(cellValue);
		cell.setCellStyle(boldCellStyle);

		// create cell until last cell of format
		while (cellCount < maxCellCount) {
			cell = row.createCell(cellCount++);
		}

		// set cell value
		sosco = Math.round(employee.getEmployeeSalary() * 0.005 * 100.0) / 100.0;
		totalDeduction += sosco;
		cell.setCellValue(sosco);
		cellCount = 0;

		// create empty row
		row = spreadsheet.createRow(rowCount++);

		// create row and reset cell count
		row = spreadsheet.createRow(rowCount++);
		cellCount = 0;

		// set cell value
		cellValue = "Net Pay";

		// create and set cell
		cell = row.createCell(cellCount++);
		cell.setCellValue(cellValue);
		cell.setCellStyle(netPayStyleBold);

		// create cell until last cell of format
		while (cellCount < maxCellCount) {
			cell = row.createCell(cellCount++);
			cell.setCellStyle(netPayStyle);
		}

		// set cell value
		cell.setCellValue(payroll.getTotalAmount());
		cellCount = 0;

		// adjust column width for all column
		for (int i = 0; i < maxCellCount; i++) {

			spreadsheet.autoSizeColumn(i);

		}

		// Write the workbook in file system
		try {
			FileOutputStream out = new FileOutputStream(new File(filePath));
			workBook.write(out);
			out.close();
			System.out.println("Writesheet.xlsx written successfully");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
