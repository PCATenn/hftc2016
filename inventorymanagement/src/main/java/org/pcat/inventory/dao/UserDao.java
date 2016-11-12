package org.pcat.inventory.dao;

import org.pcat.inventory.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao {

	@Override
	public User getById(final int id) {
		return (User) super.getById(User.class, id);
	}

	public User getByEmailId(final String emailAddress) {
		final String hql = "select user from User user where user.email = :emailAddress";
		return (User) super.getSession().createQuery(hql).setString("emailAddress", emailAddress).uniqueResult();
	}


}
