package com.miti.ItemPublisher;

import java.util.List;

import com.mtit.model.Item;

public class ItemProcedureImp implements ItemProcedure {
	
	private List<Item> itemlist;
	
//	@Override
	 public ItemProcedureImp(List<Item> itemlist) {
        this.itemlist = itemlist;
    }
	

	
	@Override
	public List<Item> getAllItems(){
		return itemlist;
	}
	
	@Override
	public void addItem(Item item) {
		itemlist.add(item);
	}
	
	@Override
	public void editItem(String itemName, double itemPrice, double discount) {
		for (Item item : itemlist) {
			if(item.getItemName().equals(itemName)) {
				item.setItemPrice(itemPrice);
				item.setDiscount(discount);
				System.out.println(itemName + "updated");
				break;
			}
		}
		
	};
	
	@Override
	public void removeItem(String itemName) {
		itemlist.removeIf(item -> item.getItemName().equals(itemName));
	}

}
