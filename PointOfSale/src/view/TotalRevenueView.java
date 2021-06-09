package view;

import model.SaleObserver;

public class TotalRevenueView implements SaleObserver {	
    private double totalRevenue = 0;

    /**
     * Empty constructor with revenue 0.
     */
    public TotalRevenueView() {
        this.totalRevenue = 0;
    }

    /**
     * Increases total revenue with the amount of new payment.
     * @param paidAmount 
     */
    public void newPayment (double paidAmount){
        totalRevenue += paidAmount;
        displayRevenue();
    }

    /**
     * Prints the value of total revenue.
     */
    private void displayRevenue() {
        System.out.println("Totalt revenue is: " + totalRevenue);
    }
    
}
