package com.mtit.customerService;

import java.util.ArrayList;

import com.mtit.model.Customer;

public interface CustomerService {

	public String addCustomer(Customer customer);

	public ArrayList<Customer> getAllCustomers();
	
	public ArrayList<Customer> searchCustomers(String name);
	
	public String deleteCustomer(int id);
}
