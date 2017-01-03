package com.crud.rest.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.rest.beans.Flight;
public class FlightDaoImpl implements FlightDao {

	@Autowired
	private SessionFactory sessionFactory;
	//setter for sessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Flight findById(int flightID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Flight flight = new Flight();
		try{
		Criteria  criteria = (Criteria) session.createCriteria(Flight.class,"f").add(Restrictions.eq("f.flightID", flightID));
		flight =(Flight) criteria.uniqueResult();
		transaction.commit();
		session.close();
		}
		catch(Exception e)
		{
			transaction.rollback();
			session.close();
		}
		return flight;
	}
	
	@SuppressWarnings("unchecked")
	public List<Flight> findByLocal(String origin, String destination) {
		List<Flight> flight = new ArrayList<Flight>();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "select origin,destination,numberOfSeats from com.crud.rest.beans.Flight where origin = ? and destination=?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, origin);
			query.setParameter(1, destination);
			flight = (List<Flight>) query.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return flight;
	}
	@SuppressWarnings("unchecked")
	public List<Flight> findFlight(String origin, String destination,String departTime) {
		List<Flight> flight = new ArrayList<Flight>();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = " from com.crud.rest.beans.Flight where origin = ? and destination=?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, origin);
			query.setParameter(1, destination);
			flight = (List<Flight>) query.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return flight;
	}
	public Flight findByLocalandTime(String origin, String destination, String departTime) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Flight flight = new Flight();
		String hql = "from com.crud.rest.beans.Flight where origin = ? and destination=? and cast(departTime as date)=?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, origin);
			query.setParameter(1, destination);
			query.setParameter(2, departTime);
			flight = (Flight) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return flight;
	}

	public void saveFlight(Flight flight) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (flight != null) {
			try {
				session.save(flight);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void updateFlight(Flight flight) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (flight != null) {
			try {
				session.update(flight);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void deleteFlightById(int flightID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Flight flight = new Flight();
		try {
			flight = (Flight) session.get(Flight.class, flightID);
			session.delete(flight);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Flight> findAllFlights() {
		
		Session session = sessionFactory.openSession();
		Criteria  flight = (Criteria) session.createCriteria(Flight.class);
		return flight.list();
	}

	public void deleteAllFlights() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.createQuery("delete from Flight").executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}


	}

	public boolean isFlightExist(Flight flight) {
		return findById(flight.getFlightID())!=null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Flight> getList() {
	    List<Flight> flight = null;
	    Session session = sessionFactory.openSession();
	    Transaction transaction = session.beginTransaction();
	    Query query = session.createQuery("FROM com.crud.rest.beans.Flight f join fetch f.ticket_details");
	    try {
			
			flight = query.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return flight;
	}
	@SuppressWarnings("unchecked")
	public List<Flight> getListdk(Integer seat,Integer seat1, String origin, String destination, Date departTime) {
	    Session session = sessionFactory.openSession();
	    DetachedCriteria innerQuery = DetachedCriteria.forClass(Flight.class, "tinner")
	    		.createAlias("tinner.ticket_details", "t")
	    		.add(Restrictions.eqProperty("tinner.flightID", "touter.flightID"))
	    		
	    		.add(Restrictions.eq("origin", origin))
	    		.add(Restrictions.eq("destination", destination))
	    		
	    		.add(Restrictions.sqlRestriction("cast(tinner_.departTime as date)='"+departTime+"'"));;
	    innerQuery.setProjection(Projections.sqlProjection("(tinner_.numberOfSeats - sum(t1_.totalclient)) as con", new String[]{"con"}, new IntegerType[]{new IntegerType()}));
	    Criteria  criteria = (Criteria) session.createCriteria(Flight.class,"touter")
	    		.add(Subqueries.le(seat+seat1, innerQuery));
	    		
	    		
	    		
	    		
	   
		return criteria.list();
	}


}
