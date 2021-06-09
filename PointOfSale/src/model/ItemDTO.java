package model;

/**
 * Class represent an item in the store.
 */
public class ItemDTO {
	private double price;
	private int VAT;
	private String itemDescription;	
	private int itemId;
	private int quantity;
	
    /**
     * Creates an item.
     * @param price Item's price
     * @param VAT Item's VAT rate
     * @param itemDescription Item's description
     * @param itemId Item's unique identifier
     * @param quantity Item's quantity
     */
	public ItemDTO (double price, int VAT, String itemDescription, int itemId, int quantity){
		this.price = price;
		this.VAT = VAT;
		this.itemDescription = itemDescription;
		this.itemId = itemId;		
		this.quantity = quantity;
	}
	
    /**
     * @return Returns the item's price.
     */
	public double getPrice() {
		return this.price;
	}
	
	/**
     * @return Returns item's price including TAX.
     */
	public double getPriceIncludingVAT() {
		double VAT = this.VAT;
		double VATtoDecimal = VAT / 100;
		return (VATtoDecimal + 1) * this.price;
	}
	
	/**
     * @return Returns item's VAT rate.
     */
	public int getVAT() {
		return this.VAT;
	}
	
    /**
     * @return Returns item's description.
     */
	public String getItemDescription() {
		return this.itemDescription;
	}

    /**
     * @return Returns item's identifier.
     */
	public int getItemId() {
		return this.itemId;
	}
	
    /**
     * 
     * @return Returns item's quantity.
     */
	public int getQuantity() {
		return this.quantity;
	}
}
