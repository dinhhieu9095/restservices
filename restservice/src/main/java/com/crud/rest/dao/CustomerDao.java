package com.crud.rest.dao;

import java.util.List;

import com.crud.rest.beans.Customer;

public interface CustomerDao {

	Customer findById(int CustomerID);
	public String ShowFirstName(String mail);
	List<Customer> findByFirstName(String Firstname);
	Customer loginCustomer(String mail,String password);
	void saveCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomerById(int CustomerID);

	List<Customer> findAllCustomers();

	void deleteAllCustomers();

	boolean isCustomerExist(Customer Customer);
}
