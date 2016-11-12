package org.pcat.inventory.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.pcat.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao {
	@Autowired
	private SessionFactory sessionFactory;

	protected Object getById(Class clazz, int id) {
		Session session = null;
		Transaction tx = null;
		Object result = null;
		try {
			session = getSession();
			tx = getTransaction(session);
			result = session.get(clazz, id);
			tx.commit();
		} catch (Exception e) {
			handleException(e, tx);
		} finally {
			session.close();
		}

		return result;
	}

	public abstract <T extends Object> T getById(int id);

	public boolean delete(Object obj) {
		Session session = null;
		Transaction tx = null;
		boolean result = false;
		try {
			session = getSession();
			tx = getTransaction(session);
			session.delete(obj);
			tx.commit();
			result = true;
		} catch (Exception e) {
			handleException(e, tx);
		} finally {
			session.close();
		}
		return result;
	}

	public List<?> findAll(Class<?> clazz) {
		Session session = null;
		Transaction tx = null;
		List result = null;
		try {
			session = getSession();
			tx = getTransaction(session);
			Criteria criteria = session.createCriteria(User.class);
			result = criteria.list();
			tx.commit();
		} catch (Exception e) {
			handleException(e, null);
		} finally {
			session.close();
		}

		return result;
	}

	public Session getSession() {
		return sessionFactory.openSession();

	}

	public Transaction getTransaction(Session session) {
		return session.beginTransaction();
	}

	public void handleException(Exception e, Transaction tx) {
		if (tx != null) {
			tx.rollback();
		}
		throw new DataAccessLayerException(e);
	}

	public void saveOrUpdate(Object obj) {
		Session session = null;
		Transaction tx = null;

		try {
			session = getSession();
			tx = getTransaction(session);
			session.saveOrUpdate(obj);
			tx.commit();
		} catch (Exception e) {
			handleException(e, tx);
		} finally {
			session.close();
		}

	}

}
