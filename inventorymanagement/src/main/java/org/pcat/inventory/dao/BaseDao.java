package org.pcat.inventory.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDao {
	@Autowired
	private SessionFactory sessionFactory;

	protected Object getById(Class clazz, long id) {
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
	public abstract <T extends Object> T getById(long id);
	
	public List<?> findAll(Class<?> clazz) {
		if (1 == 1)
			throw new RuntimeException(
					String.format("Unemplemented call to findAll in class %s.", this.getClass().getName()));
		return null;
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
		throw new RuntimeException(String.format("Unemplemented call to saveOrUpdate(Object obj) in class %s.",
				this.getClass().getName()));

	}

}
