package controller;

import integration.ExceptionsLOG;

/**
 * Exception thrown when item can't be added for any reason(database can't be called).
 */
public class AddingItemFailedException extends Exception {
	
    /**
     * Creates a new instance of exception.
     */
    public AddingItemFailedException(String message, Exception cause) {
        super(message, cause);
        ExceptionsLOG.write(this);
        
    }
    

}
