package integration;

public class DatabaseErrorException extends RuntimeException {
	
    /**
     * Creates a new instance of exception.
     */
    public DatabaseErrorException(){
        super("<For developers> Database error.");
        ExceptionsLOG.write(this);
    }

}
