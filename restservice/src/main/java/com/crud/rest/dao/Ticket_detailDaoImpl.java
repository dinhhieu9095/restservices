package com.crud.rest.dao;

import java.util.List;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.rest.beans.Ticket_detail;

public class Ticket_detailDaoImpl implements Ticket_detailDao {

	@Autowired
	private SessionFactory sessionFactory;

	//setter for sessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Ticket_detail findById(int ticket_detailID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Ticket_detail ticket_detail = new Ticket_detail();
		try {
			Query  query = session.createQuery("FROM Ticket_detail as f left JOIN FETCH  f.tickets WHERE f.ticket_detailID="+ticket_detailID);
			ticket_detail = (Ticket_detail) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return ticket_detail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket_detail> findByCustomerID(int customerID) {
		List<Ticket_detail> ticket = new ArrayList<Ticket_detail>();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from com.crud.rest.beans.Ticket_detail where customerID like :customerID";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("customerID", customerID);
			ticket = (List<Ticket_detail>) query.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return ticket;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket_detail> findByFlightID(int flightID) {
		List<Ticket_detail> ticket = new ArrayList<Ticket_detail>();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from com.crud.rest.beans.Ticket_detail as f left JOIN FETCH  f.tickets where flightID like :flightID";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("flightID", flightID);
			ticket = (List<Ticket_detail>) query.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return ticket;
	}

	@Override
	public void saveTicket_detail(Ticket_detail ticket_detail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (ticket_detail != null) {
			try {
				session.save(ticket_detail);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void deleteAllTicketsOfCustomer(int customerID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			Query query = session.createQuery("delete from Ticket_detail where customerID like :customerID");
			query.setParameter("customerID", customerID);
			query.executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}
	@Override
	public void updateTicket_detail(Ticket_detail ticket_detail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (ticket_detail != null) {
			try {
				session.update(ticket_detail);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}
	@Override
	public void confirmTicket_detail(Ticket_detail ticket_detail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (ticket_detail != null) {
			try {
				session.update(ticket_detail);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}
	}

	@Override
	public void deleteTicket_detailById(int ticket_detailID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Ticket_detail ticket_detail = new Ticket_detail();
		try {
			ticket_detail = (Ticket_detail) session.get(Ticket_detail.class, ticket_detailID);
			session.delete(ticket_detail);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket_detail> findAllTicket_details() {
		List<Ticket_detail> ticket = new ArrayList<Ticket_detail>();
		Session session = sessionFactory.openSession();
		ticket = session.createQuery("From com.crud.rest.beans.Ticket_detail order by time desc").list();
		return ticket;
	}

	@Override
	public void deleteAllTicket_details() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.createQuery("delete from Ticket_detail").executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	@Override
	public boolean isTicket_detailExist(Ticket_detail ticket_detail) {
		// TODO Auto-generated method stub
		return check(ticket_detail.getFlightID(),ticket_detail.getCustomerID())!=null;
	}
	@Override
	public Ticket_detail check(int flightID,int customerID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Ticket_detail ticket_detail = new Ticket_detail();
		String hql = "from com.crud.rest.beans.Ticket_detail where flightID = ? and customerID =?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, flightID);
			query.setParameter(1, customerID);
			ticket_detail = (Ticket_detail) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return ticket_detail;
	}
}
