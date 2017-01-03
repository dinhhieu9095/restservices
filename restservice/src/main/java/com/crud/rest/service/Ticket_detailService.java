package com.crud.rest.service;

import java.util.List;

import com.crud.rest.beans.Ticket_detail;

public interface Ticket_detailService {
	Ticket_detail findById(int ticket_detailID);
	List<Ticket_detail> findByCustomerID(int customerID);
	void saveTicket_detail(Ticket_detail ticket_detail);
	void confirmTicket_detail(Ticket_detail ticket_detail);
	void updateTicket_detail(Ticket_detail ticket_detail);
	List<Ticket_detail> findByFlightID(int flightID);
	void deleteTicket_detailById(int ticket_detailID);
	List<Ticket_detail> findAllTicket_details();

	void deleteAllTicket_details();

	boolean isTicket_detailExist(Ticket_detail ticket_detail);
}
