package model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate; 

/**
 * Represents information that needs to be printed.
 */
public class Receipt {
	private String storeName;
	private String storeAddress;
	private ArrayList<ItemDTO> itemList;
	private ArrayList<Integer> itemQuantity;
	private double totalCost;
	private double totalVAT;
	private double amountPaid;
	private double change;
	
    /**
     * Creates an instance of receipt with sale information
     * @param sale current sale
     */
    public Receipt(Sale sale) {
		storeName = "IV1350 Store";
		storeAddress = "Isafjordsgatan 22";
		itemList = sale.getItemList();
	    itemQuantity = sale.getItemQuantityList();
	    totalCost = sale.getRunningTotal();
	    totalVAT = sale.getRunningTotal() - sale.getTotal();
		amountPaid = sale.getAmountPaid();
		change = sale.getChange();
    }
	
    /**
     * @return Time of sale.
     */
    public String getTime() {
    LocalTime time = LocalTime.now();
	return time.toString();  
    }
    
    /**
     * @return Date of sale.
     */
    public String getDate() {
    LocalDate date = LocalDate.now();
	return date.toString();  
    }
    
    /**
     * @return Store's name.
     */
    public String getStoreName() {
	return storeName;
    }
    
    /**
     * @return Store's address.
     */
    public String getStoreAddress() {
	return storeAddress;
    }
    
    /**
     * @return reference to the list of items with all information. 
     */
    public ArrayList<ItemDTO> getItemList() {
        return itemList;
    }
    
    /**
     * @return reference to the list of item quantity.
     */
    public ArrayList<Integer> getItemQuantity() {
        return this.itemQuantity;
    }
    
    /**
     * @return The total cost of the sale
     */
    public double getTotalCost() {
	return totalCost;
    }
    
    /**
     * @return Total VAT for the sale
     */
    public double getTotalVAT() {
	return totalVAT;
    }
    
    /**
     * @return The amount of cash received.
     */
    public double getAmountPaid() {
        return amountPaid;
    }
    
    /**
     * @return The amount of cash received.
     */
    public double getChange() {
        return change;
    }

}
