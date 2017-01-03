package com.crud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.crud.rest.beans.Customer;
import com.crud.rest.beans.Ticket;
import com.crud.rest.beans.Ticket_detail;
import com.crud.rest.service.CustomerService;
import com.crud.rest.service.TicketService;
import com.crud.rest.service.Ticket_detailService;

@RestController // combination of @Controller and @ResponseBody annotations
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	//setter for customerService
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	@Autowired
	private Ticket_detailService ticket_detailService;

	//setter for customerService
	public void setTicket_detailService(Ticket_detailService ticket_detailService) {
		this.ticket_detailService = ticket_detailService;
	}
	@Autowired
	private TicketService ticketService;

	//setter for customerService
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	// Add Customer
	@RequestMapping(value = "/customer/new", method = RequestMethod.POST)
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer, UriComponentsBuilder ucb) {
		if (customerService.isCustomerExist(customer)) {
			return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
		} else {

			customerService.saveCustomer(customer);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucb.path("/customer/{CustomerID}").buildAndExpand(customer.getCustomerID()).toUri());
			return new ResponseEntity<Customer>(customer,HttpStatus.CREATED);
		}
	}

	// Get Name
	@RequestMapping(value = "/name/{mail}", method = RequestMethod.GET)
	public ResponseEntity<String> showname(@PathVariable("mail") String mail) {
		String fn=customerService.ShowFirstName(mail);
		if (fn == null) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>(fn,HttpStatus.OK);
	}
	// Get Single Customer
	@RequestMapping(value = "/customer/{CustomerID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getCustomer(@PathVariable("CustomerID") int CustomerID) {

		Customer customer = customerService.findById(CustomerID);
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	// login Customer
		@RequestMapping(value = "/login/{mail}/{password}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Customer> loginCustomer(@PathVariable("mail") String mail,@PathVariable("password") String password) {
			Customer customer = customerService.loginCustomer(mail, password);
			if (customer == null) {
				return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
			}
	// Get All Customers
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAllCustomers() {
		List<Customer> customers = customerService.findAllCustomers();
		if (customers.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	// Update Customer
	@RequestMapping(value = "/customer/{CustomerID}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateCustomer(@PathVariable("CustomerID") int CustomerID, @RequestBody Customer cus) {

		Customer customer = customerService.findById(CustomerID);

		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

		customer.setFirstName(cus.getFirstName());
		customer.setLastName(cus.getLastName());
		customer.setPassword(cus.getPassword());
		customer.setGender(cus.getGender());
		customer.setAge(cus.getAge());
		customer.setCity(cus.getCity());
		customer.setPhone(cus.getPhone());
		customer.setMail(cus.getMail());

		customerService.updateCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// Delete Customer
	@RequestMapping(value = "/customer/{CustomerID}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("CustomerID") int CustomerID) {

		Customer customer = customerService.findById(CustomerID);
		if (customer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

		customerService.deleteCustomerById(CustomerID);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

	// Delete All Customers
	@RequestMapping(value = "/customer/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteAllCustomers() {

		customerService.deleteAllCustomers();
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
	//search
	@RequestMapping(value = "/search/{Firstname}", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listCustomers(@PathVariable("Firstname") String FirstName) {
		List<Customer> customer = customerService.findByFirstName(FirstName);
		if (customer.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
	}	
	//
	@RequestMapping(value = "/decustomer/{customerID}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteTicketID(@PathVariable("customerID") int customerID) {

		Customer cutomer = customerService.findById(customerID);
		if (cutomer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		List<Ticket_detail> ticket_detail = cutomer.getTicket_details();
		for (Ticket_detail t : ticket_detail) {
			List<Ticket> ticket = t.getTickets();
			for (Ticket x : ticket) {
				ticketService.deleteTicketById(x.getTicketID());
			}
			ticket_detailService.deleteTicket_detailById(t.getTicket_detailID());
		}
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
	//
	@RequestMapping(value = "/deletecustomer/{customerID}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deletecustomerID(@PathVariable("customerID") int customerID) {

		Customer cutomer = customerService.findById(customerID);
		if (cutomer == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		List<Ticket_detail> ticket_detail = cutomer.getTicket_details();
		for (Ticket_detail t : ticket_detail) {
			List<Ticket> ticket = t.getTickets();
			for (Ticket x : ticket) {
				ticketService.deleteTicketById(x.getTicketID());
			}
			ticket_detailService.deleteTicket_detailById(t.getTicket_detailID());
		}
		customerService.deleteCustomerById(customerID);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
}
