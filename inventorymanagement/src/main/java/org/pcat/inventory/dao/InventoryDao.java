package org.pcat.inventory.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.Inventory;
import org.pcat.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class to perform User Management operations.
 * 
 * @author venkat
 *
 */
@Repository
public class InventoryDao implements BaseDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BaseDao baseDao;

	public void delete(Object obj) {
		baseDao.delete(obj);
	}

	/**
	 * Method to delete inventory information.
	 * 
	 * @param inventory
	 * @return
	 */
	public boolean deleteInventory(Inventory inventory) {
		boolean isDeleted = false;
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Inventory.class);
			criteria.add(Restrictions.eq("id", inventory.getId()));
			Inventory updateInventory = (Inventory) criteria.list().get(0);
			session.delete(updateInventory);
			isDeleted = true;
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	public <Inventory extends Object> Inventory getById(Long id) {
		return (Inventory) baseDao.getById(id);
	}

	public List<?> findAll(Class<?> clazz) {
		return baseDao.findAll(clazz);
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public Inventory getById(long id) {
		Session session = null;
		Inventory inventory = null;
		try {
			session = sessionFactory.openSession();
			inventory = (Inventory) session.get(Inventory.class, id);
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return inventory;
	}

	public Session getSession() {
		return baseDao.getSession();
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Transaction getTransaction(Session session) {
		return baseDao.getTransaction(session);
	}

	public void handleException(Exception e, Transaction tx) {
		baseDao.handleException(e, tx);
	}

	public List<FamilyInventory> listAllFamilyInventory() {
		List<FamilyInventory> inventoryList = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(FamilyInventory.class);
			criteria.add(Restrictions.eq("status", "pending"));
			inventoryList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inventoryList;

	}

	/**
	 * Method to List All Inventories information.
	 * 
	 * @param user
	 * @return
	 */
	public List<Inventory> listAllInventories() {
		List<Inventory> inventories = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Inventory.class);
			inventories = (List<Inventory>)criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return inventories;
	}

	public List<Inventory> listAllInventory() {
		List<Inventory> inventoryList = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Inventory.class);
			inventoryList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inventoryList;

	}

	/**
	 * Method to save Inventory information.
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveInventory(Inventory inventory) {
		boolean isSaved = false;
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(inventory);
			tx.commit();
			isSaved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSaved;
	}

	public void saveOrUpdate(Object obj) {
		baseDao.saveOrUpdate(obj);
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Method to update inventory information.
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateInventory(Inventory inventory) {
		boolean isUpdated = false;
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Inventory.class);
			criteria.add(Restrictions.eq("id", inventory.getId()));
			Inventory updateInventory = (Inventory) criteria.list().get(0);
			updateInventory.setProductName(inventory.getProductName());
			updateInventory.setProductDesc(inventory.getProductDesc());
			updateInventory.setTotalInventory(inventory.getTotalInventory());
			session.update(updateInventory);
			isUpdated = true;
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}
}
