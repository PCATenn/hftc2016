package org.pcat.inventory.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pcat.inventory.model.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public Transaction getTransaction(Session session) {
		return super.getTransaction(session);
	}

	public void handleException(Exception e, Transaction tx) {
		super.handleException(e, tx);
	}


	public List<Inventory> listAll() {
		return (List<Inventory>) super.findAll(Inventory.class);

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
		Session session = null;
		try {
			session = getSession();
			tx = session.beginTransaction();
			session.save(inventory);
			tx.commit();
			isSaved = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSaved;
	}

	public void saveOrUpdate(Object obj) {

		super.saveOrUpdate(obj);
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
		Session session = null;
		try {
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Inventory.class);
			criteria.add(Restrictions.eq("id", inventory.getId()));
			Inventory updateInventory = (Inventory) criteria.list().get(0);
			updateInventory.setProductName(inventory.getProductName());
			updateInventory.setProductDesc(inventory.getProductDesc());
			updateInventory.setTotalInventory(inventory.getTotalInventory());
			getSession().update(updateInventory);
			isUpdated = true;
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isUpdated;
	}

	public Collection<Inventory> getCollectionByIds(final Collection<Integer> ids) {
		// TODO: Add test to the integration
		List<Inventory> returnList = new ArrayList<Inventory>();
		ids.forEach(id -> returnList.add(this.getById(id)));
		return returnList;
	}

}
