package com.crud.rest.dao;
import java.util.List;
import com.crud.rest.beans.Ticket_detail;
public interface Ticket_detailDao {
	Ticket_detail findById(int ticket_detailID);
	List<Ticket_detail> findByCustomerID(int customerID);
	List<Ticket_detail> findByFlightID(int flightID);
	void saveTicket_detail(Ticket_detail ticket_detail);
	void confirmTicket_detail(Ticket_detail ticket_detail);
	void updateTicket_detail(Ticket_detail ticket_detail);

	void deleteTicket_detailById(int ticket_detailID);

	List<Ticket_detail> findAllTicket_details();
	void deleteAllTicket_details();
	Ticket_detail check(int flightID,int customerID);
	boolean isTicket_detailExist(Ticket_detail ticket_detail);
}
