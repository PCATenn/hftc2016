package org.pcat.inventory.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pcat.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	private User user;

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
	 * This method validates the logged in user using the email and
	 *  returns back the user or null if not valid.
	 * @param email user email used to login
	 * @return User if valid else null.
	 */
	public User validateUserLogin(String email) {
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("email", email));
			if( criteria.list().isEmpty() == false)
			{
				user = ((User) criteria.list().get(0));
				return user;
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
}
