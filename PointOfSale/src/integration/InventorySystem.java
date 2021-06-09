package integration;

import model.ItemDTO;
import model.Sale;

/**
 * Represents the store's inventory system with items that are available for sale.
 */
public class InventorySystem {
	private ItemDTO inventory[] = new ItemDTO[10];
	
    /**
     * Creates inventory system with a list of items that are available for sale.
     */
	public InventorySystem() {
        this.inventory[0] = new ItemDTO(15.0, 6, "Water", 0, 0);
        this.inventory[1] = new ItemDTO(17.0, 6, "Milk", 1, 0);
        this.inventory[2] = new ItemDTO(19.0, 12, "Juice", 2, 0);
        this.inventory[3] = new ItemDTO(14.0, 25, "Beer", 3, 0);
        this.inventory[4] = new ItemDTO(22.0, 6, "Bread", 4, 0);
        this.inventory[5] = new ItemDTO(12.0, 6, "Rice", 5, 0);
        this.inventory[6] = new ItemDTO(20.0, 6, "Watermelon", 6, 0);
        this.inventory[7] = new ItemDTO(68.0, 25, "Cigarettes", 7, 0);
        this.inventory[8] = new ItemDTO(100.0, 6, "Book", 8, 0);
        this.inventory[9] = new ItemDTO(5.0, 12, "Pen", 9, 0);
	}
	
	/**
	 * Looks for the item identifier in the inventory system.
	 * If there is no matching identifier in inventory system return null.
     * @param itemId Item's unique identifier.
     * @return Return matched item from inventory system
     */
	public ItemDTO findItem(int itemId) throws NonExistingIdentifierException, DatabaseErrorException {
		for(int i = 0; i < inventory.length; i++) {
            if (itemId == 10){
                throw new DatabaseErrorException();
            } 
			
			if(inventory[i].getItemId() == itemId) {
				return inventory[i];
			}
		}
		throw new NonExistingIdentifierException(itemId);
	}
	
    /**
     * Saves sale information in the inventory system.
     * @param sale Information about the sale to be saved.
     */
	public void saveSaleInformation(Sale sale) {
	        System.out.println("Inventory system update executed");
	}

}
