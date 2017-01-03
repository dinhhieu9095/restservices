package com.crud.rest.dao;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.rest.beans.Ticket;

public class TicketDaoImpl implements TicketDao {

	@Autowired
	private SessionFactory sessionFactory;

	//setter for sessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Ticket findById(int ticketID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Ticket ticket = new Ticket();
		try {
			ticket = (Ticket) session.get(Ticket.class, ticketID);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return ticket;
	}

	@SuppressWarnings("unchecked")
	public List<Ticket> findByTicket_detailID(int ticket_detailID) {
		List<Ticket> ticket = new ArrayList<Ticket>();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from com.crud.rest.beans.Ticket where ticket_detailID like :ticket_detailID";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("ticket_detailID", ticket_detailID);
			ticket = (List<Ticket>) query.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return ticket;
	}

	public void saveTicket(Ticket ticket) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (ticket != null) {
			try {
				session.save(ticket);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void updateTicket(Ticket ticket) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (ticket != null) {
			try {
				session.update(ticket);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void deleteTicketById(int ticketID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Ticket ticket = new Ticket();
		try {
			ticket = (Ticket) session.get(Ticket.class, ticketID);
			session.delete(ticket);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Ticket> findAllTickets() {
		List<Ticket> ticket = new ArrayList<Ticket>();
		Session session = sessionFactory.openSession();
		ticket = session.createQuery("From com.crud.rest.beans.Ticket").list();
		return ticket;
	}

	public void deleteAllTickets() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.createQuery("delete from Ticket").executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}


	public boolean isTicketExist(Ticket ticket) {
		// TODO Auto-generated method stub
		return findById(ticket.getTicketID())!=null;
	}
	public Ticket findByName(String name) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Ticket ticket = new Ticket();
		String hql = "from com.crud.rest.beans.Ticket where name = ?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, name);
			ticket = (Ticket) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return ticket;
	}

}
