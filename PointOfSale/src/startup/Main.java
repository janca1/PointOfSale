package startup;

import controller.Controller;
import controller.AddingItemFailedException;
import view.View;
import integration.SystemCreator;
import integration.InventorySystem;
import integration.AccountingSystem;
import integration.ExceptionsLOG;
import integration.NonExistingIdentifierException;
import model.CashRegister;
import java.io.IOException;

/**
 * Class Main starts the application. Method main is responsible for starting the application
 */
public class Main {
	/**
	 * The main method used to start the entire application. 
	 * 
	 * @param args The application does not take any command line parameters. 
	 */
	public static void main(String[] args) throws NonExistingIdentifierException, AddingItemFailedException, IOException{
		SystemCreator creator = new SystemCreator();
		InventorySystem InventorySystem = creator.getInventorySystem();
		AccountingSystem AccountingSystem = creator.getAccountingSystem();
		CashRegister CashRegister = new CashRegister();
		Controller contr = new Controller (InventorySystem, AccountingSystem, CashRegister);
		ExceptionsLOG log = new ExceptionsLOG();
		View view = new View(contr);	
		
		view.runFakeExecution(creator);
		view.saleTestRun();
		view.endSale();
		view.pay(500);
		view.printReceipt();	
		
		view.runFakeExecution(creator);
		view.saleTestRunTwo();
		view.endSale();
		view.pay(500);
		view.printReceipt();
		
	}
}
