package model;

/**
 * Interface for sale objects.
 */
public interface SaleObserver {
    /**
     * Gets called for each payment.
     * @param sale Current sale that is being paid.
     */
    void newPayment (double amount);
}
