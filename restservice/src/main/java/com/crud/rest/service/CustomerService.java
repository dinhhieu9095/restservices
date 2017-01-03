package com.crud.rest.service;

import java.util.List;

import com.crud.rest.beans.Customer;

public interface CustomerService {

	Customer findById(int CustomerID);
	public String ShowFirstName(String mail);
	List<Customer> findByFirstName(String Firstname);
	void saveCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomerById(int CustomerID);

	List<Customer> findAllCustomers();

	void deleteAllCustomers();

	boolean isCustomerExist(Customer customer);
	Customer loginCustomer(String mail,String password);
}
