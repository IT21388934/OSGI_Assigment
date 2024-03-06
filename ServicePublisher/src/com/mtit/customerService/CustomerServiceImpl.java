package com.mtit.customerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mtit.model.Customer;
//import com.mtit.util.DBConnectionUtil;
import com.mtit.util.Database;

public class CustomerServiceImpl implements CustomerService {

	private static Connection con;

	private static Statement st;

	private static PreparedStatement pst;

	private static ResultSet rs;

	@Override
	public synchronized String addCustomer(Customer customer) {
		try {
			Database.customerList.add(customer);
			return "Customer added successfully.";
		} catch (Exception e) {
			return "Couldn't add the customer.";
		}
	}

	@Override
	public synchronized ArrayList<Customer> getAllCustomers() {
//		ArrayList<Customer> customers = new ArrayList<Customer>();
//		con = DBConnectionUtil.getDBConnection();
//		try {
//			st = con.createStatement();
//			rs = st.executeQuery("SELECT * FROM customer");
//			
//			while (rs.next()) {
//				Customer customer = new Customer();
//				customer.setId(rs.getInt(1));
//				customer.setName(rs.getString(2));
//				customer.setNic(rs.getString(3));
//				customer.setLoyaltyCard(rs.getString(4));
//				customer.setPoints(rs.getDouble(5));
//				
//				customers.add(customer);
//			}
//			
//			return customers;
//			
//		} catch (Exception e) {
//			System.out.println("Coudn't get customer list.");
//			return null;
//		}

		try {
			return Database.customerList;
		} catch (Exception e) {
			System.out.println("Coudn't get customer list.");
			return null;
		}

	}

	@Override
	public ArrayList<Customer> searchCustomers(String name) {
		ArrayList<Customer> result = new ArrayList<Customer>();

		for (Customer customer : Database.customerList) {
			if (customer.getName().matches("(.*)" + name + "(.*)")) {
				result.add(customer);
			}
		}

		return result;
	}

	@Override
	public String deleteCustomer(int id) {
		try {
			for (Customer customer : Database.customerList) {
				int i = 0;
				if (customer.getId() == id) {
					Database.customerList.remove(i);
					break;
				}
				i++;
			}

			return "Customer deleted.";

		} catch (Exception e) {
			return "Couldn't delete te customer.";
		}

	}

}
