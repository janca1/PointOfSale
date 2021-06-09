package integration;

/**
 * Class SystemCreator creates instances of external systems.
 */
public class SystemCreator {
	private AccountingSystem accountingSystem = new AccountingSystem();
	private InventorySystem inventorySystem = new InventorySystem();
	
	/**
	 * Returns existing accounting system information.
	 */
	public AccountingSystem getAccountingSystem() {
		return this.accountingSystem;
	}
	
	/**
	 * Returns existing inventory system information.
	 */
	public InventorySystem getInventorySystem() {
		return this.inventorySystem;
	}

}
