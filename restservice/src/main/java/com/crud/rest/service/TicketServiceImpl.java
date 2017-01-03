package com.crud.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.Ticket;
import com.crud.rest.dao.TicketDao;

public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketDao ticketDao;
	//setter for customerDao
		public void setTicketDao(TicketDao ticketDao) {
			this.ticketDao = ticketDao;
		}
	@Override
	public Ticket findById(int ticketID) {
		// TODO Auto-generated method stub
		return ticketDao.findById(ticketID);
	}

	@Override
	public List<Ticket> findByTicket_detailID(int ticket_detailID) {
		// TODO Auto-generated method stub
		return ticketDao.findByTicket_detailID(ticket_detailID);
	}

	@Override
	public void saveTicket(Ticket ticket) {
		ticketDao.saveTicket(ticket);
		
	}

	@Override
	public Ticket findByName(String name) {
		// TODO Auto-generated method stub
		return ticketDao.findByName(name);
	}

	@Override
	public void updateTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		ticketDao.updateTicket(ticket);
	}

	@Override
	public void deleteTicketById(int ticketID) {
		// TODO Auto-generated method stub
		ticketDao.deleteTicketById(ticketID);
	}

	@Override
	public List<Ticket> findAllTickets() {
		// TODO Auto-generated method stub
		return ticketDao.findAllTickets();
	}

	@Override
	public void deleteAllTickets() {
		// TODO Auto-generated method stub
		ticketDao.deleteAllTickets();
	}

	@Override
	public boolean isTicketExist(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketDao.isTicketExist(ticket);
	}

}
