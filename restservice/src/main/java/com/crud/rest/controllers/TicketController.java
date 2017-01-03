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
public class TicketController {
	@Autowired
	private TicketService ticketService;

	//setter for customerService
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}
	@Autowired
	private Ticket_detailService ticket_detailService;

	//setter for customerService
	public void setTicket_detailService(Ticket_detailService ticket_detailService) {
		this.ticket_detailService = ticket_detailService;
	}
	//
	@RequestMapping(value = "/ticket/new", method = RequestMethod.POST)
	public ResponseEntity<Void> addTicket(@RequestBody Ticket ticket, UriComponentsBuilder ucb) {
		if (ticketService.isTicketExist(ticket)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {

			ticketService.saveTicket(ticket);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucb.path("/ticket/{ticketID}").buildAndExpand(ticket.getTicketID()).toUri());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	//
	@RequestMapping(value = "/ticket/{ticketID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ticket> getTicket(@PathVariable("ticketID") int ticketID) {

		Ticket ticket = ticketService.findById(ticketID);
		if (ticket == null) {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
	}
	//
	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public ResponseEntity<List<Ticket>> listAllTickets() {
		List<Ticket> tickets = ticketService.findAllTickets();
		if (tickets.isEmpty()) {
			return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
	}
	//
	@RequestMapping(value = "/ticket/{ticketID}", method = RequestMethod.PUT)
	public ResponseEntity<Ticket> updateTicket(@PathVariable("ticketID") int ticketID, @RequestBody Ticket tic) {

		Ticket ticket = ticketService.findById(ticketID);

		if (ticket == null) {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}

		ticket.setName(tic.getName());;

		ticketService.updateTicket(ticket);;
		return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
	}
	//
	@RequestMapping(value = "/ticket/{ticketID}", method = RequestMethod.DELETE)
	public ResponseEntity<Ticket> deleteTicket(@PathVariable("ticketID") int ticketID) {

		Ticket ticket = ticketService.findById(ticketID);
		if (ticket == null) {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}

		ticketService.deleteTicketById(ticketID);
		return new ResponseEntity<Ticket>(HttpStatus.NO_CONTENT);
	}
	//
	@RequestMapping(value = "/deticket/{ticket_detailID}", method = RequestMethod.DELETE)
	public ResponseEntity<Ticket> deleteTicketID(@PathVariable("ticket_detailID") int ticket_detailID) {

		Ticket_detail ticket_detail = ticket_detailService.findById(ticket_detailID);
		if (ticket_detail == null) {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
		List<Ticket> ticket = ticket_detail.getTickets();
		for (Ticket t : ticket) {
			ticketService.deleteTicketById(t.getTicketID());
		}
		ticket_detailService.deleteTicket_detailById(ticket_detailID);
		return new ResponseEntity<Ticket>(HttpStatus.NO_CONTENT);
	}

	//
	@RequestMapping(value = "/ticket/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<Ticket> deleteAllTickets() {
		ticketService.deleteAllTickets();
		return new ResponseEntity<Ticket>(HttpStatus.NO_CONTENT);
	}
	//
	@RequestMapping(value = "/searchticket/{ticket_detailID}", method = RequestMethod.GET)
	public ResponseEntity<List<Ticket>> listtickets(@PathVariable("ticket_detailID") int ticket_detailID) {
		List<Ticket> ticket = ticketService.findByTicket_detailID(ticket_detailID);
		if (ticket.isEmpty()) {
			return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Ticket>>(ticket, HttpStatus.OK);
	}
}
