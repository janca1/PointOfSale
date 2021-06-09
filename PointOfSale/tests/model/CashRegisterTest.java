package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CashRegisterTest {

    @Test
    public void testUpdateRegister() {
        double payment = 50;
        CashRegister register = new CashRegister();
        register.updateRegister(payment);
        assertEquals(50, register.getBalance(), "Register not updated with correct amount!");
    }
    
    @Test
    public void testUpdateRegisterNegative() {
        double payment = -50;
        CashRegister register = new CashRegister();
        register.updateRegister(payment);
        assertEquals(-50, register.getBalance(), "Register not updated correctly!");
    }
    
    @Test
    public void testCalculateChange() {
        double payment = 100.0;
        double cost = 80.0;
        CashRegister register = new CashRegister();
        double expected = 20.0;
        double result = register.calculateChange(payment, cost);
        assertEquals(expected, result, "Incorrect amount of change returned!");
    }
    
    @Test
    public void testCalculateChangeInsufficientPayment(){
        double payment = 50.0;
        double cost = 100.0;
        CashRegister register = new CashRegister();
        double expResult = -50.0;
        double result = register.calculateChange(payment, cost);
        assertEquals(expResult, result, "Incorrect amount of change returned!");
    }

}
