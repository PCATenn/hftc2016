package org.pcat.inventory.service;

import org.pcat.inventory.dao.UserDao;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public HomeVisitor getHomeVisitor(String emailAddress) {
		User user = userDao.getByEmailId(emailAddress);
		return new HomeVisitor(user.getFirstname(), user.getLastname(), user.getEmail(), user.getSupervisoremail());
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
