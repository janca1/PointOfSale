package integration;

import model.Sale;

/**
 * Store's accounting system responsible for storing sale information.
 */
public class AccountingSystem {
    /**
     * Creates an instance of the accounting system
     */
	public AccountingSystem() {
		
	}
	
	/**
     * Saves sale information in accounting system. 
     * @param sale contains sale information
     */
	public void saveSaleInformation(Sale sale) {
	        System.out.println("Accounitng system update executed");
			System.out.println("----------------------------------------------------------");
	}

}
