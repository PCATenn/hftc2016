package org.pcat.inventory.dao;

import java.util.List;

import org.pcat.inventory.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao {

	public List<User> findAll() {
		return (List<User>) super.findAll(User.class);
	}

	public User getByEmailId(final String emailAddress) {
		final String hql = "select user from User user where user.email = :emailAddress";
		return (User) super.getSession().createQuery(hql).setString("emailAddress", emailAddress).uniqueResult();
	}

	@Override
	public User getById(final int id) {
		return (User) super.getById(User.class, id);
	}

	public List<User> getBySupervisorEmail(final String supervisorEmail) {
		final String hql = "select user from User user where user.supervisorEmail = :supervisorEmail";
		return (List<User>) super.getSession().createQuery(hql).setString("supervisorEmail", supervisorEmail).list();
	}

}
