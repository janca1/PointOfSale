package controller;

import model.Sale;
import model.ItemDTO;
import integration.InventorySystem;
import integration.AccountingSystem;
import model.CashRegister;
import integration.SystemCreator;
import model.Receipt;
import integration.NonExistingIdentifierException;
import integration.DatabaseErrorException;
import view.TotalRevenueFileOutput;
import view.TotalRevenueView;	

/**
 * This is application's only controller. All calls to the model pass through this class.
 */
public class Controller {
	private Sale sale;
    private InventorySystem inventorySystem;
    private AccountingSystem accountingSystem;
    private CashRegister cashRegister;
    private double runningTotal;
	public TotalRevenueView revenueView = new TotalRevenueView();
	public TotalRevenueFileOutput revenueFileOutput = new TotalRevenueFileOutput();

    
    /**
    * Creates a new instance of controller with references to external systems.
    * @param inventorySystem Inventory system that has a list of all products.
    * @param accountingSystem Accounting system where sales are saved.
    * @param cashRegister Cash register that contains cash and information about balance.
    */
    public Controller(InventorySystem inventorySystem, AccountingSystem accountingSystem, CashRegister cashRegister) {
    	this.inventorySystem = inventorySystem;
    	this.accountingSystem = accountingSystem;
    	this.cashRegister = cashRegister;
    }
	
	/**
	 * Starts new sale. This method must be called before doing anything else during a sale.
	 */
	public void startSale(SystemCreator creator) {
		sale = new Sale(creator);
        sale.startCashRegister();
        sale.addSaleObserver(revenueView);
        sale.addSaleObserver(revenueFileOutput);
	}
	
    /**
     * scanItem looks for item identification and adds scanned item to the current sale.
     * @param itemId is item's unique identifier.
     * @return method returns matched item found in inventory system.
     * If there is no such item, method will return null
     */
    public ItemDTO scanItem(int itemId) throws 
    NonExistingIdentifierException, AddingItemFailedException {
    	try {
	    	ItemDTO foundItem = inventorySystem.findItem(itemId);
	    	if(foundItem != null) {
		    	sale.addItem(foundItem);
		    	return foundItem;
	    	} 
	    	else {
	    		return null;
	    	}
    	} 
    	catch (DatabaseErrorException exc) {
    	     throw new AddingItemFailedException("Item couldn't be added.(Database error)" , exc);
    	}
    }
    
    /**
     * @return Returns the current cost of the sale
     */
    public double getRunningTotal() {
    	runningTotal = sale.getRunningTotal();
    	return runningTotal;
    }
    
    /**
     * pay calls method pay from the class Sale which returns the amount of change if enough cash is inputed. 
     * @param amount represents how much cash customer has paid.
     * @return returns the amount of change that should be given to customer if enough cash is given to cashier.
     */
    public double pay (double amount) {
    	 if(amount >= sale.getRunningTotal()) {
            double change = sale.pay(amount);
            return change;
         } 
    	 
    	 else {
            return -1.0;
         }
    }
    
    /**
     * Creates an instance of the receipt with sale information.
     * @return an instance of the receipt.
     */
    public Receipt requestReceipt() {
    	Receipt receipt = new Receipt(sale);
    	return receipt;
    }

}
