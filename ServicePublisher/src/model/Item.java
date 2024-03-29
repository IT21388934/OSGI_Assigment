package model;

public class Item {
	
	private String itemName;
	private double itemPrice;
	private double discount;
	
	//constructor
	public Item(String itemName, double itemPrice, double discount) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.discount = discount;
	}

	//getters and setters
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	@Override
	public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discount=" + discount +
                '}';
    }
	
	
}
