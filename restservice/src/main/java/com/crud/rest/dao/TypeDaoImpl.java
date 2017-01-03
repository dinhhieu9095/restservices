package com.crud.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.crud.rest.beans.Type;

public class TypeDaoImpl implements TypeDao {

	@Autowired
	private SessionFactory sessionFactory;

	//setter for sessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void saveType(Type type) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (type != null) {
			try {
				session.save(type);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void updateType(Type type) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (type != null) {
			try {
				session.update(type);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	public void deleteTypeById(int TypeID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Type type = new Type();
		try {
			type = (Type) session.get(Type.class, TypeID);
			session.delete(type);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	@SuppressWarnings("unchecked")

	public List<Type> findAllTypes() {
		List<Type> type = new ArrayList<Type>();
		Session session = sessionFactory.openSession();
		type = session.createQuery("From com.crud.rest.beans.Type").list();
		return type;
	}


	public void deleteAllTypes() {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.createQuery("delete from Type").executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	public boolean isTypeExist(Type type) {
		// TODO Auto-generated method stub
		return findByName(type.getType())!=null;
	}

	public Type findByID(int TypeID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Type type = new Type();
		try {
			type = (Type) session.get(Type.class, TypeID);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return type;
	}

	public Type findByName(String type) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Type typ = new Type();
		String hql = "from com.crud.rest.beans.Type where type = ?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, type);
			typ = (Type) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return typ;
	}

}
