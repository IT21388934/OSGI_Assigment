package com.miti.ItemPublisher;

import java.util.List;

import model.Item;

public interface ItemProcedure {
	
//	//constructor 
//	public String ItemProcedure();
	
	//method to item list
	public List<Item> getAllItems();
	
	//add new item to the item list
	public void addItem(Item item);
	
	//edit item by itemName
	public void editItem(String itemName, double itemPrice, double discount);
	
	//delete item by itemName
	public void removeItem(String itemName);

}
