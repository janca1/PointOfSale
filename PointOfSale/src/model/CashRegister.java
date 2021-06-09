package model;

/**
 * Represents store's cash register with it's balance balance.
 */
public class CashRegister {
	 private double balance = 0.0;
	    
	/**
	 * addPayment Represents adding cash into the register.
	 * @param amountPaid Amount of cash added to cash register.
	 */
	public void updateRegister(double amountPaid)
	{
	    this.balance += amountPaid;
	}
	
	/**
	 * Calculates the change that should be given to the customer.
	 * @param amountPaid How much cash customer gave.
	 * @param cost Total cost for the sale.
	 * @return Returns how much change should be given.
	 */
	public double calculateChange(double amountPaid, double cost)
	{
	    double change = amountPaid - cost;
	    return change;
	}
	
	/**
	 * Gets the cash balance from the register.
	 * @return Returns how much cash there is in the register.
	 */
	public double getBalance (){
	    return this.balance;
	}

}
