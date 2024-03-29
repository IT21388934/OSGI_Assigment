package itemconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.miti.ItemPublisher.ItemProcedure;

import model.Item;

public class ItemConsumerActivator implements BundleActivator {

	ServiceReference serviceReference;
	
	// Initialize a Scanner object to read input from the console
	Scanner sc = new Scanner(System.in);

	public void start(BundleContext context) throws Exception {
		System.out.println("===========================Item Consumer bundle started.============================");
		
		
		// Obtain a service reference for the ItemProcedure class
		serviceReference = context.getServiceReference(ItemProcedure.class);

		// Create an instance of ItemProcedure using the obtained service reference
		ItemProcedure itemProducer = (ItemProcedure) context.getService(serviceReference);

	
		// Set a boolean flag to control the loop
		boolean running = true;

		// Enter a loop that continues as long as the "running" flag is true
		while (running) {
		    // Display a menu of options for the user
			System.out.println("============Welcome to Item Mangement====================");
		    System.out.println("Choose an option:");
		    System.out.println("1. View All Items");
		    System.out.println("2. Add Item");
		    System.out.println("3. Edit Item");
		    System.out.println("4. Delete Item");
		    System.out.println("5. Exit");
		    System.out.print("Option: ");
		    
		    // Read the user's choice (an integer) from the console
		    int option = sc.nextInt();
		    
		    switch(option) {
		    case 1:
		    	getAllItems(itemProducer);
		    	break;
		    case 2:
		    	addItem(itemProducer);
		    	break;
		    case 3:
		    	editItem(itemProducer);
		    	break;
		    	
		    case 4:
		    	removeItem(itemProducer);
		    	break;
		    case 5:
                running = false;
                break;
            default:
                System.out.println("Invalid option.");
		    }
		     
		}
		
		
	}

	private void removeItem(ItemProcedure itemProducer) {
		// TODO Auto-generated method stub
		System.out.print("Enter Item Name to delete: ");
        String itemName = sc.next();
        itemProducer.removeItem(itemName);
        System.out.println("Item deleted successfully.");
		
	}

	private void editItem(ItemProcedure itemProducer) {
		// TODO Auto-generated method stub
		System.out.println("Enter Item Name :");
		String itemName = sc.next();
		System.out.println("Enter Price of the Item :");
		double newItemPrice = sc.nextDouble();
		System.out.println("Enter Discount : ");
		double newDiscount = sc.nextDouble();
		
		itemProducer.editItem(itemName, newItemPrice, newDiscount);
		
	}

	private void addItem(ItemProcedure itemProducer) {
		// TODO Auto-generated method stub
		System.out.println("Enter Item Name :");
		String itemName = sc.next();
		System.out.println("Enter Price of the Item :");
		double itemPrice = sc.nextDouble();
		System.out.println("Enter Discount : ");
		double discount = sc.nextDouble();
		
		Item item = new Item(itemName,itemPrice,discount);
		itemProducer.addItem(item);
		
	}

	private void getAllItems(ItemProcedure itemProducer) {
		// TODO Auto-generated method stub
		List<Item> itemList = itemProducer.getAllItems();
        for (Item item : itemList) {
            System.out.println(item);
        }
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	System.out.println("Item Consumer bundle stopped.");
        context.ungetService(serviceReference);
	}
	
	public void uninstall(BundleContext context) throws Exception{
		System.out.println("===========Good Bye==========");
		context.getBundle().stop();
	}

}
