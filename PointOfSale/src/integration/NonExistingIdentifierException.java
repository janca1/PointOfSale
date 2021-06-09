package integration;

/**
 * Exception thrown when item identifier is not found in inventory catalogue.
 */
public class NonExistingIdentifierException extends Exception {
	
    /**
     * Creates new instance of exception.
     * @param itemId Identifier that can't be found in inventory.
     */
    public  NonExistingIdentifierException (int itemId){
        super("Item identifier: " + itemId + " can't be found in inventory.");
        ExceptionsLOG.write(this);
    }

}
