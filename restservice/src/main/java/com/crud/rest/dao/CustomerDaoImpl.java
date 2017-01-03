package com.crud.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.Customer;

public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	//setter for sessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Customer findById(int CustomerID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = new Customer();
		try {
			Criteria  criteria = (Criteria) session.createCriteria(Customer.class,"c").add(Restrictions.eq("c.customerID", CustomerID));
			customer = (Customer) criteria.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return customer;
	}

	public Customer loginCustomer(String mail,String password) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = new Customer();
		String hql = "from com.crud.rest.beans.Customer where mail = ? and password =?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, mail);
			query.setParameter(1, password);
			customer = (Customer) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return customer;
	}

	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (customer != null) {
			try {
				session.save(customer);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void updateCustomer(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (customer != null) {
			try {
				session.update(customer);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void deleteCustomerById(int CustomerID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Customer customer = new Customer();
		try {
			customer = (Customer) session.get(Customer.class, CustomerID);
			session.delete(customer);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Customer> findAllCustomers() {
		List<Customer> customer = new ArrayList<Customer>();
		Session session = sessionFactory.openSession();
		customer = session.createQuery("From com.crud.rest.beans.Customer").list();
		return customer;
	}
	@SuppressWarnings("unchecked")
	public List<Customer> findByFirstName(String FirstName) {
		List<Customer> customer = new ArrayList<Customer>();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "from com.crud.rest.beans.Customer where Firstname like :FirstName";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("FirstName", FirstName);
			customer = (List<Customer>) query.list();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return customer;
	}
	public void deleteAllCustomers() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.createQuery("delete from Customer").executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}
	public String ShowFirstName(String mail)
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		String hql = "select firstName from com.crud.rest.beans.Customer where mail = ?";
		String fn ="";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, mail);
			fn = (String) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return fn;
	}

	public boolean isCustomerExist(Customer Customer) {
		
			return loginCustomer(Customer.getMail(),Customer.getPassword())!=null;
	}


}
