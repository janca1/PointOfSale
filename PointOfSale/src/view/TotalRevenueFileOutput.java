package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.SaleObserver;

public class TotalRevenueFileOutput implements SaleObserver {

	private double totalRevenue = 0;
	private String fileName = "RevenueOutputFile.txt";

	/**
	 * Empty constructor with revenue 0.
	 */
	public TotalRevenueFileOutput() {
		this.totalRevenue = 0;
		File file = new File(fileName);
		if(file.exists()) {
			file.delete();
		}
	}

	/**
	 * Increases total revenue with the amount of new payment.
	 * @param paidAmount
	 */
	public void newPayment(double paidAmount) {
		totalRevenue += paidAmount;
		displayRevenue();
	}

	/**
	 * Prints the value of total revenue to a file.
	 */
	private void displayRevenue() {

		try {
			FileWriter fWriter = new FileWriter(fileName, true);
			fWriter.write("Total revenue is: "+totalRevenue+"\n");
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
