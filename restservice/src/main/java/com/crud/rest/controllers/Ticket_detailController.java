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

import com.crud.rest.beans.Ticket;
import com.crud.rest.beans.Ticket_detail;
import com.crud.rest.service.TicketService;
import com.crud.rest.service.Ticket_detailService;
@RestController // combination of @Controller and @ResponseBody annotations
public class Ticket_detailController {
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
	//
	@RequestMapping(value = "/ticket_detail/new", method = RequestMethod.POST)
	public ResponseEntity<Ticket_detail> addTicket_detail(@RequestBody Ticket_detail ticket_detail, UriComponentsBuilder ucb) {
		if (ticket_detailService.isTicket_detailExist(ticket_detail)) {
			return new ResponseEntity<Ticket_detail>(HttpStatus.CONFLICT);
		} else {

			ticket_detailService.saveTicket_detail(ticket_detail);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucb.path("/ticket_detail/{ticket_detailID}").buildAndExpand(ticket_detail.getTicket_detailID()).toUri());
			return new ResponseEntity<Ticket_detail>(ticket_detail,HttpStatus.CREATED);
		}
	}
	@RequestMapping(value = "/tickets/new", method = RequestMethod.POST)
	public ResponseEntity<Void> addTicket(@RequestBody Ticket_detail ticket_detail, UriComponentsBuilder ucb) {
		
			
			List<Ticket> ticket = ticket_detail.getTickets();
			for (Ticket t : ticket) {
				ticketService.saveTicket(t);
			}
			return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	@RequestMapping(value = "/ticket_detail/new2", method = RequestMethod.POST)
	public ResponseEntity<Void> addTicket_detai(@RequestBody Ticket_detail ticket_detail, UriComponentsBuilder ucb) {
		if (ticket_detailService.isTicket_detailExist(ticket_detail)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {
			ticket_detailService.saveTicket_detail(ticket_detail);
			List<Ticket> ticket = ticket_detail.getTickets();
			for (Ticket t : ticket) {
				ticketService.saveTicket(t);
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucb.path("/ticket_detail/{ticket_detailID}").buildAndExpand(ticket_detail.getTicket_detailID()).toUri());
			return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
		}
	}
	//
	@RequestMapping(value = "/ticket_detail/{ticket_detailID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ticket_detail> getTicket_detail(@PathVariable("ticket_detailID") int ticket_detailID) {

		Ticket_detail ticket_detail = ticket_detailService.findById(ticket_detailID);
		if (ticket_detail == null) {
			return new ResponseEntity<Ticket_detail>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Ticket_detail>(ticket_detail, HttpStatus.OK);
	}
	//
	@RequestMapping(value = "/ticket_details", method = RequestMethod.GET)
	public ResponseEntity<List<Ticket_detail>> listAllTicket_details() {
		List<Ticket_detail> ticket_details = ticket_detailService.findAllTicket_details();
		if (ticket_details.isEmpty()) {
			return new ResponseEntity<List<Ticket_detail>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ticket_detail>>(ticket_details, HttpStatus.OK);
	}
	//
	@RequestMapping(value = "/updatetic/{ticket_detailID}", method = RequestMethod.PUT)
	public ResponseEntity<Ticket_detail> updateTicket_detail(@PathVariable("ticket_detailID") int ticket_detailID, @RequestBody Ticket_detail tic) {

		Ticket_detail ticket_detail = ticket_detailService.findById(ticket_detailID);
		if (ticket_detail == null) {
			return new ResponseEntity<Ticket_detail>(HttpStatus.NOT_FOUND);
		}
		ticket_detail.setFlightID(tic.getFlightID());
		ticket_detail.setCustomerID(tic.getCustomerID());
		ticket_detail.setTotalclient(tic.getTotalclient());
		ticket_detail.setTotalprice(tic.getTotalprice());
		ticket_detail.setTotaltax(tic.getTotaltax());
		ticket_detail.setConfirm(tic.isConfirm());
		ticket_detailService.updateTicket_detail(ticket_detail);
		return new ResponseEntity<Ticket_detail>(ticket_detail, HttpStatus.OK);
	}
	//
	@RequestMapping(value = "/ticket_detail/{ticket_detailID}", method = RequestMethod.DELETE)
	public ResponseEntity<Ticket_detail> deleteTicket_detail(@PathVariable("ticket_detailID") int ticket_detailID) {

		Ticket_detail ticket_detail = ticket_detailService.findById(ticket_detailID);
		if (ticket_detail == null) {
			return new ResponseEntity<Ticket_detail>(HttpStatus.NOT_FOUND);
		}

		ticket_detailService.deleteTicket_detailById(ticket_detailID);
		return new ResponseEntity<Ticket_detail>(HttpStatus.NO_CONTENT);
	}
	//
	@RequestMapping(value = "/confirmticket_detail/{ticket_detailID}", method = RequestMethod.PUT)
	public ResponseEntity<Void> confirmTicket_detail(@PathVariable("ticket_detailID") int ticket_detailID) {

		Ticket_detail ticket_detail = ticket_detailService.findById(ticket_detailID);
		if (ticket_detail == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		ticket_detail.setConfirm(true);
		ticket_detailService.confirmTicket_detail(ticket_detail);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	//
	@RequestMapping(value = "/ticket_detail/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<Ticket_detail> deleteAllTicket_details() {
		ticket_detailService.deleteAllTicket_details();
		return new ResponseEntity<Ticket_detail>(HttpStatus.NO_CONTENT);
	}
	//
	@RequestMapping(value = "/searchticket_detail/{customerID}", method = RequestMethod.GET)
	public ResponseEntity<List<Ticket_detail>> listticket_details(@PathVariable("customerID") int customerID) {
		List<Ticket_detail> ticket_detail = ticket_detailService.findByCustomerID(customerID);
		if (ticket_detail.isEmpty()) {
			return new ResponseEntity<List<Ticket_detail>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ticket_detail>>(ticket_detail, HttpStatus.OK);
	}
}
