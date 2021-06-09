package model;

import java.util.ArrayList;
import integration.AccountingSystem;
import integration.InventorySystem;
import integration.SystemCreator;
import java.util.List;

/**
 * One single sale made by one single customer and payed with one payment.
 */
public class Sale {	
    private ArrayList<ItemDTO> itemList = new ArrayList<ItemDTO>();
    private ArrayList<Integer> itemQuantity = new ArrayList<Integer>();
    boolean itemScannedFlag = false;
    private double runningTotal = 0.0;
    private double total = 0.0;
    private double payment;
    private CashRegister cashRegister; 
    private double change = 0.0;
    private SystemCreator creator = new SystemCreator();
    private List<SaleObserver> saleObservers = new ArrayList<>();
	
	/**
	 * Creates a new instance of the sale.
	 */
	public Sale (SystemCreator creator) {
		this.creator = creator;
	}
	
	/**
	 * Starts cash register.
	 */
	public void startCashRegister() {
		cashRegister = new CashRegister();
	}
	
    /**
     * addItem updates itemList 
     * If the list is empty item gets added instantly.
     * If the list contains an item with same identifier as the newly scanned item, the quantity will be incremented.
     * If newly scanned item is not the first item nor there is item with same identifier in the list, item will be added to the list.
     * @param item item we are adding to the list.
     */
    public void addItem(ItemDTO item) {	
		if(itemList.isEmpty()) {
	            itemList.add(item);
	            itemQuantity.add(1);
	            itemScannedFlag = true;
		}
		
		for(int i = 0; i < itemList.size(); i++) {
        	if(itemScannedBefore(item, i)) {
				int currentQuantity = itemQuantity.get(i);
                itemQuantity.set(i,currentQuantity + 1);
				itemScannedFlag = true;
           	} 
		}
		
		if(!itemScannedFlag){ 
            itemList.add(item);
            itemQuantity.add(1);
		}
	        
		itemScannedFlag = false;
		calculateRunningTotal(item);
		calculateTotal(item);
    }
    
    private boolean itemScannedBefore(ItemDTO item, int i) {
        if (item.getItemId() == itemList.get(i).getItemId() && itemScannedFlag == false){
            return true;
        }   
        else
            return false;
    }
    
    private void calculateRunningTotal(ItemDTO foundItem) {
    	 this.runningTotal += foundItem.getPriceIncludingVAT();
    }
       
    /**
     * @return The running total cost of the sale. (VAT included)
     */
    public double getRunningTotal() {
    	return this.runningTotal;
    }
    
    private void calculateTotal(ItemDTO foundItem) {
   	 this.total += foundItem.getPrice();
   }
      
   /**
    * @return The running total cost of the sale. (VAT included)
    */
   public double getTotal() {
   	return this.total;
   }
    
    /**
     * @return Returns the list of items in a sale.
     */
    public ArrayList<ItemDTO> getItemList() {
    	return this.itemList;
    }
    
    /**
     * @return list with item quantities.
     */
    public ArrayList<Integer> getItemQuantityList() {
        return this.itemQuantity;
    }
    
    /**
     * pay represents the process of paying, calculating change, updating cash register and external systems.
     * @param payment The amount cash customer paid.
     * @return The amount of change that should be given to the customer.
     */
    public double pay(double payment) {
	    this.payment = payment;
	    
	    double totalPrice = getRunningTotal();
	    change = cashRegister.calculateChange(payment, totalPrice);
	    cashRegister.updateRegister(payment - change);
	    notifyObservers(this.runningTotal);
	
	    InventorySystem inventorySystem = creator.getInventorySystem();
	    AccountingSystem accountingSystem = creator.getAccountingSystem();
	    inventorySystem.saveSaleInformation(this);
	    accountingSystem.saveSaleInformation(this);
	    
	    return change;
    }
    
    /**
     * @return Amount of cash that customer paid.
     */
    public double getAmountPaid() {
    	return payment;
    }
    
    /**
     * @return Amount of change that should be given to customer.
     */
    public double getChange() {
	return change;
    }
    
	/**
     * Adds an observer to the list saleObservers
     * @param saleObserver The observer to be added to the list.
     */
    public void addSaleObserver(SaleObserver saleObserver) {
        saleObservers.add(saleObserver);
    }
    
    private void notifyObservers(double paidAmount) {
        for(SaleObserver obs: saleObservers) {
            obs.newPayment(paidAmount);
        }
    }
	
}
