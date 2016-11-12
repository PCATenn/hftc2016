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

	private HomeVisitor buildHomeVisitor(User user) {
		return new HomeVisitor(user.getFirstname(), user.getLastname(), user.getEmail(), user.getSupervisoremail());
	}

	public HomeVisitor getHomeVisitor(int userId) {
		User user = userDao.getById(userId);
		return buildHomeVisitor(user);
	}

	public HomeVisitor getHomeVisitorByEmailAddress(String emailAddress) {
		User user = userDao.getByEmailId(emailAddress);
		return buildHomeVisitor(user);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


}
