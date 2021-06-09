package integration;

import model.ItemDTO;
import model.Receipt;
import java.util.ArrayList;

/**
 * Represents store's Printer as a separate system. 
 */
public class Printer {

    /**
     * Prints all necessary information about a sale onto a receipt
     * @param receipt Contains the information to be printed
     */
    public void printReceipt(Receipt receipt) {
    	System.out.println("----------------------------------------------------------");
		System.out.println("Receipt:");
		System.out.println("Date: " + receipt.getDate() + " Time: " + receipt.getTime());
		System.out.println("Store: " + receipt.getStoreName());
		System.out.println("Address: " + receipt.getStoreAddress());
		System.out.println("**********************************************************");

		ArrayList<ItemDTO> itemList = receipt.getItemList();
	    ArrayList<Integer> itemQuantity = receipt.getItemQuantity();
	    
		for(int i = 0; i < receipt.getItemList().size(); i++) {
            System.out.print(itemList.get(i).getItemDescription());
            System.out.print(" Quantity:" + itemQuantity.get(i));
            System.out.print(" Price: " + itemList.get(i).getPrice() * itemQuantity.get(i));
            System.out.println("");
		}
		
		System.out.println("**********************************************************");
		System.out.println("Total cost: " + receipt.getTotalCost() + " Total VAT: " + receipt.getTotalVAT());
		System.out.println("Amount paid: " + receipt.getAmountPaid() + " Change: " + receipt.getChange());
		System.out.println();
    }

}
