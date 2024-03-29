package com.miti.ItemPublisher;

import java.awt.ItemSelectable;
import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import model.Item;

public class ItemProcedureActivator implements BundleActivator {

	public List<Item> itemList; 
	ServiceRegistration serviceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("Start Item producer");
		
		itemList = new ArrayList<>();
        itemList.add(new Item("Item1", 10.0, 0));
        itemList.add(new Item("Item2", 20.0, 5));

        ItemProcedure service = new ItemProcedureImp(itemList);
        serviceRegistration = context.registerService(ItemProcedure.class.getName(), service, null);

	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Item producer stopped");
		serviceRegistration.unregister();
		
	}

}
