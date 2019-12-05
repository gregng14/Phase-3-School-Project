package com.cgi.web.model;

public class CustomerService {
	
	public Customer findCustomer(int custid) {
//		return CustomerDB.findCustomer(custid);
		Customer customer = CustomerDB.findCustomer(custid);
		customer.setCustFirstName("Char");
		return customer;
	}
	
	public Customer findCustomer(String userid) {
//		return CustomerDB.findCustomersByNameEquals(userid);
		Customer customer = CustomerDB.findCustomersByNameEquals(userid);
		customer.setCustFirstName("Char");
		customer.setPassword("pass1");
		if(userid.equals(customer.getCustFirstName())){
			return customer;
		}
		return null;
	}
	
	public void insertCustomer(Customer customer){
		CustomerDB.insertCustomer(customer);
	}
	
	public Customer updateCustomer(Customer customer) {
		return CustomerDB.updateCustomer(customer);
	}
	
	public Customer loginCustomer(String userId, String password) {
		/*Customer customer = this.findCustomer(userId);
		if(customer != null && customer.getPassword().equals(password)) {
			return customer;
		}
		return null;*/
		Customer customer = this.findCustomer(userId);

		if(customer != null && customer.getPassword().equals(password)) {
			return customer;
		}
		return null;
	}

}
