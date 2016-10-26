package org.pcat.inventory.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public interface BaseDao {
	public void delete(Object obj);

	public Object find(Class<?> clazz, Long id);

	public List<?> findAll(Class<?> clazz);

	public Session getSession();

	public Transaction getTransaction(Session session);

	public void handleException(Exception e, Transaction tx);

	public void saveOrUpdate(Object obj);

}
