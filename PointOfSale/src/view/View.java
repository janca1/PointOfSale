package view;

import java.io.IOException;
import controller.AddingItemFailedException;
import integration.NonExistingIdentifierException;
import controller.Controller;
import model.ItemDTO;
import model.Receipt;
import integration.Printer;
import integration.SystemCreator;

/**
 * Class View is a placeholder for the real view. It contains a hardcoded execution with calls to all system operations in the controller.
 */
public class View {
	private Controller contr;
	
	/**
	 * Creates a new instance, that uses the specified controller for all calls to other layers.
	 * 
	 * @param contr The controller to use for all calls to other layers.
	 */
	public View (Controller contr) {
		this.contr = contr;
	}
	
	/**
	 * Performs a fake sale, by calling all system operations in the controller.
	 */
	public void runFakeExecution(SystemCreator creator) {
		contr.startSale(creator);
		System.out.println("A new sale has been started!");
		System.out.println("----------------------------------------------------------");
	}
	
	/**
	 * Ends sale. All items have been scanned.
	 */
	public void endSale() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Sale ended!");
		System.out.println("Total price including VAT: " + contr.getRunningTotal() );
		System.out.println("----------------------------------------------------------");
	}
	
    /**
     * Scan item, add it to the sale and display information on screen. 
     * In case of invalid identifier message will be displayed.
     * @param itemId Item's unique identifier.
     */
    public void addItem(int itemId) throws 
    NonExistingIdentifierException, AddingItemFailedException, IOException {
    	
    	try {
    	ItemDTO currentItem = contr.scanItem(itemId);
    	displayItemInfo(currentItem);
    	}
    	
    	catch (NonExistingIdentifierException exc) {
            System.out.println(exc.getMessage());
        }
    	
        catch (AddingItemFailedException exc) {
            System.out.println(exc.getMessage());
         }
    }
    
    private void displayItemInfo(ItemDTO itemToPrint) {
    	System.out.println("Item: " + itemToPrint.getItemDescription() + " Price: " + itemToPrint.getPrice() +
    						" Running Total: " + contr.getRunningTotal());
    }
    
    /**
     * Test run of a sale process. Simulates the process of scanning items and displaying information.
     */
   public void saleTestRun() throws NonExistingIdentifierException, AddingItemFailedException, IOException {
		addItem(2);
		addItem(10);
		addItem(4);
		addItem(11);
		addItem(2);
		addItem(2);
    } 
   
   /**
    * Another test run of a sale process. Simulates the process of scanning items and displaying information.
    */
  public void saleTestRunTwo() throws NonExistingIdentifierException, AddingItemFailedException, IOException {
		addItem(1);
		addItem(5);
		addItem(8);
   } 
    
    
    /**
     * pay calls for the method pay from Controller which returns how much change should be given to the customer.
     * @param amount The amount of cash that customer paid.
     */
    public void pay(double amount) {
    	double amountPaid = amount;
    	double change = contr.pay(amount);
    	
        if(change >= 0) {
            displayAmountAndChange(amountPaid, change);
        } 
        
        else {
            System.out.println("Not enough cash paid.");
        }   	
    }
    
    private void displayAmountAndChange(double amountPaid, double change) {
    	System.out.println("Amount paid : " + amountPaid + " Change: " + change);
    	System.out.println("Cash register update executed");
    }
    
    /**
     * Prints the receipt.
     */
    public void printReceipt() {
    	Receipt receipt = contr.requestReceipt();
        Printer printer = new Printer();
        printer.printReceipt(receipt);
        
    }
    
}
