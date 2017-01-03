package com.crud.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.Ticket_detail;
import com.crud.rest.dao.Ticket_detailDao;

public class Ticket_detailServiceImpl implements Ticket_detailService {
	@Autowired
	private Ticket_detailDao ticket_detailDao;
	//setter for customerDao
		public void setTicket_detailDao(Ticket_detailDao ticket_detailDao) {
			this.ticket_detailDao = ticket_detailDao;
		}
	@Override
	public Ticket_detail findById(int ticket_detailID) {
		// TODO Auto-generated method stub
		return ticket_detailDao.findById(ticket_detailID);
	}

	@Override
	public List<Ticket_detail> findByCustomerID(int customerID) {
		// TODO Auto-generated method stub
		return ticket_detailDao.findByCustomerID(customerID);
	}

	@Override
	public void saveTicket_detail(Ticket_detail ticket_detail) {
		ticket_detailDao.saveTicket_detail(ticket_detail);		
	}

	@Override
	public void updateTicket_detail(Ticket_detail ticket_detail) {
		ticket_detailDao.updateTicket_detail(ticket_detail);
		
	}

	@Override
	public void deleteTicket_detailById(int ticket_detailID) {
		ticket_detailDao.deleteTicket_detailById(ticket_detailID);
		
	}

	@Override
	public List<Ticket_detail> findAllTicket_details() {
		// TODO Auto-generated method stub
		return ticket_detailDao.findAllTicket_details();
	}

	@Override
	public void deleteAllTicket_details() {
		ticket_detailDao.deleteAllTicket_details();
		
	}

	@Override
	public boolean isTicket_detailExist(Ticket_detail ticket_detail) {
		// TODO Auto-generated method stub
		return ticket_detailDao.isTicket_detailExist(ticket_detail);
	}
	@Override
	public List<Ticket_detail> findByFlightID(int flightID) {
		// TODO Auto-generated method stub
		return ticket_detailDao.findByFlightID(flightID);
	}
	@Override
	public void confirmTicket_detail(Ticket_detail ticket_detail) {
		ticket_detailDao.confirmTicket_detail(ticket_detail);
		
	}

}
