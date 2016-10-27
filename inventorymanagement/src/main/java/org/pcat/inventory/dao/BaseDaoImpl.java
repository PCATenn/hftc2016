package org.pcat.inventory.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDaoImpl implements BaseDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public <Object> Object getById(Long id) {
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

	@Override
	public List<?> findAll(Class<?> clazz) {
		// TODO Auto-generated method stub
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

	@Override
	public void saveOrUpdate(Object obj) {
		// TODO Auto-generated method stub

	}

}
