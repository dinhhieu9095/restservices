package com.crud.rest.service;

import java.util.List;

import com.crud.rest.beans.Ticket;

public interface TicketService {
	Ticket findById(int ticketID);
	List<Ticket> findByTicket_detailID(int ticket_detailID);
	void saveTicket(Ticket ticket);
	Ticket findByName(String name);
	void updateTicket(Ticket ticket);

	void deleteTicketById(int ticketID);

	List<Ticket> findAllTickets();

	void deleteAllTickets();

	boolean isTicketExist(Ticket ticket);
}
