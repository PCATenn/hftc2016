package org.pcat.inventory.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pcat.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Class to perform User Management operations.
 * 
 * @author venkat
 *
 */
@Repository
public class UserManagementDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Method to save user information.
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user) {
		boolean isSaved = false;
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			isSaved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSaved;
	}

	/**
	 * Method to update user information.
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user) {
		boolean isUpdated = false;
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("id", user.getId()));
			User updateUser = (User) criteria.list().get(0);
			updateUser.setFirstname(user.getFirstname());
			updateUser.setLastname(user.getLastname());
			updateUser.setRole(user.getRole());
			updateUser.setSupervisor(user.getSupervisor());
			session.update(updateUser);
			isUpdated = true;
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	/**
	 * Method to delete user information.
	 * 
	 * @param user
	 * @return
	 */
	public boolean deleteUser(User user) {
		boolean isDeleted = false;
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("id", user.getId()));
			User deleteUser = (User) criteria.list().get(0);
			session.delete(deleteUser);
			isDeleted = true;
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isDeleted;
	}

	/**
	 * Method to List All users information.
	 * 
	 * @param user
	 * @return
	 */
	public List<User> listAllUsers() {
		List<User> users = null;
		try {
			Session session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(User.class);
			users = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
