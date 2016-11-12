package org.pcat.inventory.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Class to perform User Management operations.
 * 
 * @author venkat
 *
 */
@Repository
public class InventoryDao extends BaseDao {
	private static final Logger logger = LoggerFactory.getLogger(InventoryDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public List<Inventory> findAll() {
		return (List<Inventory>) super.findAll(Inventory.class);
	}

	public Inventory getById(int id) {
		final Inventory inventory = (Inventory) super.getById(Inventory.class, id);
		return inventory;
	}

	public Session getSession() {
		return super.getSession();
	}

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Transaction getTransaction(Session session) {
		return super.getTransaction(session);
	}

	public void handleException(Exception e, Transaction tx) {
		super.handleException(e, tx);
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
			inventories = (List<Inventory>) criteria.list();
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

		super.saveOrUpdate(obj);
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

	public Collection<Inventory> getCollectionById(final Collection<Integer> ids) {
		// TODO: Add test to the integration
		List<Inventory> returnList = new ArrayList<Inventory>();
		ids.forEach(id -> returnList.add(this.getById(id)));
		return returnList;
	}

}
