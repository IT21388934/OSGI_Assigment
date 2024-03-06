package com.mtit.activator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.miti.ItemPublisher.ItemProcedure;
import com.miti.ItemPublisher.ItemProcedureImp;
import com.mtit.customerService.CustomerService;
import com.mtit.customerService.CustomerServiceImpl;
import com.mtit.model.Item;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration serviceRegistration;
	Scanner scanner = new Scanner(System.in);
	String type;
	public List<Item> itemList;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Service Publisher Start");

		System.out.println("Enter 1 to manage items.");
		System.out.println("Enter 2 to manage customers.");
		type = scanner.nextLine();

		if (type.equals("1")) {
			itemList = new ArrayList<>();
			itemList.add(new Item("Item1", 10.0, 0));
			itemList.add(new Item("Item2", 20.0, 5));

			ItemProcedure service = new ItemProcedureImp(itemList);
			serviceRegistration = bundleContext.registerService(ItemProcedure.class.getName(), service, null);
		} else if (type.equals("2")) {
			CustomerService customerService = new CustomerServiceImpl();
			serviceRegistration = bundleContext.registerService(CustomerService.class.getName(), customerService, null);
		}

	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Publisher Stop");
		serviceRegistration.unregister();
	}

}
