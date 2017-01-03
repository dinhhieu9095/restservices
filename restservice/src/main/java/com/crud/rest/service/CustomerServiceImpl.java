package com.crud.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.Customer;
import com.crud.rest.dao.CustomerDao;

public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	//setter for customerDao
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	@Override
	public List<Customer> findByFirstName(String Firstname) {

		return customerDao.findByFirstName(Firstname);
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerDao.saveCustomer(customer);

	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);

	}

	@Override
	public void deleteCustomerById(int CustomerID) {
		customerDao.deleteCustomerById(CustomerID);

	}

	@Override
	public List<Customer> findAllCustomers() {

		return customerDao.findAllCustomers();
	}

	@Override
	public void deleteAllCustomers() {
		customerDao.deleteAllCustomers();

	}

	@Override
	public boolean isCustomerExist(Customer Customer) {

		return customerDao.isCustomerExist(Customer);
	}

	@Override
	public String ShowFirstName(String mail) {
		// TODO Auto-generated method stub
		return customerDao.ShowFirstName(mail);
	}
	@Override
	public Customer loginCustomer(String mail, String password) {
		// TODO Auto-generated method stub
		return customerDao.loginCustomer(mail, password);
	}
	@Override
	public Customer findById(int CustomerID) {
		// TODO Auto-generated method stub
		return customerDao.findById(CustomerID);
	}


}
