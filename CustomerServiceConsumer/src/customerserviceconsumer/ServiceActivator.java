package customerserviceconsumer;

import com.mtit.customerService.CustomerService;
import com.mtit.model.Customer;

import java.util.ArrayList;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;
	Scanner scanner = new Scanner(System.in);
	private static final int LIMIT = 10000;
	private static int last = 0;

	public void start(BundleContext bundleContext) {
		System.out.println("Starting Customer Service Consumer");
		serviceReference = bundleContext.getServiceReference(CustomerService.class.getName());
		CustomerService customerService = (CustomerService) bundleContext.getService(serviceReference);

		String type;

		while (true) {
			System.out.println(
					"Enter 1 to add a customer.\nEnter 2 to display all customers.\nEnter 3 to search customers.\nEnter 4 to remove a customer.\nEnter 5 to exit.");

			type = scanner.nextLine();

			try {
				switch (type) {
				case "1": {
					addCustomer(customerService);
					break;
				}
				case "2": {
					displayCustomers(customerService);
					break;
				}
				case "3": {
					displaySearchResult(customerService);
					break;
				}
				case "4": {
					deleteCustomer(customerService);
					break;
				}
				case "5": {
					return;
				}
				default:
					System.out.println("Invalid input.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("Stop Customer Service Consumer.");
		bundleContext.ungetService(serviceReference);
	}

	private void addCustomer(CustomerService customerService) throws Exception {
		String name;
		String nic;
		String phone;

		System.out.println("===========================Add a Customer===========================\n");

		System.out.print("Enter name: ");
		name = scanner.nextLine();
		System.out.print("Enter nic: ");
		nic = scanner.nextLine();
		System.out.print("Enter phone: ");
		phone = scanner.nextLine();

		Customer customer = new Customer();
		customer.setName(name);
		customer.setNic(nic);
		customer.setPhone(phone);
		customer.setLoyaltyCard(String.valueOf(generateID()));
		customer.setPoints(0.0);

		System.out.println(customerService.addCustomer(customer));
	}

	private int generateID() {

		int id = (int) (System.currentTimeMillis() % LIMIT);
		if (id <= last) {
			id = (last + 1) % LIMIT;
		}

		return last = id;

	}

	private void displayCustomers(CustomerService customerService) throws Exception {
		ArrayList<Customer> customers = customerService.getAllCustomers();

		System.out.println(
				"=============================================Customer List=========================================\n");

		System.out.println(
				"===================================================================================================");
		System.out.println("|Name\t\t|NIC\t\t|Phone Number\t\t|Loyalty Card\t\t|Points\t\t\t|");
		System.out.println(
				"===================================================================================================");

		for (Customer customer : customers) {
			System.out.println(customer.getName() + "\t\t" + customer.getNic() + "\t\t" + customer.getPhone() + "\t\t\t"
					+ customer.getLoyaltyCard() + "\t\t\t" + customer.getPoints());
		}
	}

	private void displaySearchResult(CustomerService customerService) throws Exception {
		System.out.print("\nEnter customer name: ");
		String name = scanner.nextLine();
		ArrayList<Customer> result = customerService.searchCustomers(name);

		if (result.isEmpty()) {
			System.out.println("There is no matching customer.");
		} else {
			System.out.println(
					"================================================Results============================================\n");

			System.out.println(
					"===================================================================================================");
			System.out.println("|Name\t\t|NIC\t\t|Phone Number\t\t|Loyalty Card\t\t|Points\t\t\t|");
			System.out.println(
					"===================================================================================================");
			for (Customer customer : result) {
				System.out.println(customer.getName() + "\t\t" + customer.getNic() + "\t\t" + customer.getPhone()
						+ "\t\t\t" + customer.getLoyaltyCard() + "\t\t\t" + customer.getPoints());
			}
		}
	}

	private void deleteCustomer(CustomerService customerService) throws Exception {
		System.out.print("Enter customer id: ");
		int id = scanner.nextInt();

		System.out.println(customerService.deleteCustomer(id));
	}
}
