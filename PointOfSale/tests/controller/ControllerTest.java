package controller;

//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import integration.AccountingSystem;
import integration.InventorySystem;
import integration.SystemCreator;
import model.CashRegister;
import model.ItemDTO;
import model.Receipt;
import integration.DatabaseErrorException;
import integration.NonExistingIdentifierException;

public class ControllerTest {

    SystemCreator creator = new SystemCreator();
    InventorySystem inventorySystem = creator.getInventorySystem();
    AccountingSystem accountingSystem = creator.getAccountingSystem();
    CashRegister cashRegister = new CashRegister();
    Controller contr = new Controller(inventorySystem, accountingSystem, cashRegister);
    
    @Test
    public void testScanListedItem() throws NonExistingIdentifierException, DatabaseErrorException,
    										AddingItemFailedException {
       contr.startSale(creator);
       ItemDTO item = contr.scanItem(0);
       assertEquals(0, item.getItemId(), "Item not scanned correctly");
    }
    
    /*
    @Test
    public void testScanNonListedItem() throws NonExistingIdentifierException, DatabaseErrorException,
											   AddingItemFailedException {
       contr.startSale(creator);
       ItemDTO item = contr.scanItem(10);
       assertEquals(null, item, "Non listed item scanned incorrectly");
    }
    */
    
    @Test
    public void testScanNonListedItem() throws NonExistingIdentifierException, DatabaseErrorException,
											   AddingItemFailedException {
        try {
        	contr.startSale(creator);
            ItemDTO foundItem = contr.scanItem(10); // Invalid identifier
            fail("Exception was not thrown");
        } catch (AddingItemFailedException exc) {
            assertTrue(exc.getMessage().contains("Could not find item with id: "), "Wrong exception message.");
        }
    }
    
    @Test
    public void testGetRunningTotal() throws NonExistingIdentifierException, DatabaseErrorException,
											 AddingItemFailedException {     
        contr.startSale(creator);
        contr.scanItem(0);
        contr.pay(100);
        double totalPrice = contr.getRunningTotal();
        assertEquals(15.9, totalPrice, "Incorrect total cost");
    }

    @Test
    public void testSufficientPayment() {
        contr.startSale(creator);
        double change = contr.pay(100.0);
        assertEquals(100.0, change, "Payment is not correct");
    }
    
    @Test
    public void testInsufficientPayment() {
        contr.startSale(creator);
        double change = contr.pay(-100.0);
        assertEquals(-1.0, change, "Payment is not correct");
    }
    
    @Test
    public void testRequestReceipt() {
        contr.startSale(creator);
        Receipt receipt = contr.requestReceipt();
        assertNotNull(receipt, "Receipt request failed");
    }
}
