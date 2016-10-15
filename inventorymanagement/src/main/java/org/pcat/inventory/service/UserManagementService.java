package org.pcat.inventory.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.pcat.inventory.dao.UserManagementDAO;
import org.pcat.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {

	@Autowired
	private UserManagementDAO userManagementDAO;

	/**
	 * @return the userManagementDAO
	 */
	public UserManagementDAO getUserManagementDAO() {
		return userManagementDAO;
	}

	/**
	 * @param userManagementDAO
	 *            the userManagementDAO to set
	 */
	public void setUserManagementDAO(UserManagementDAO userManagementDAO) {
		this.userManagementDAO = userManagementDAO;
	}

	/**
	 * Method to save user information.
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user) {
		return userManagementDAO.saveUser(user);
	}

	/**
	 * Method to update user information.
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user) {
		return userManagementDAO.updateUser(user);
	}

	/**
	 * Method to delete user information.
	 * 
	 * @param user
	 * @return
	 */
	public boolean deleteUser(User user) {
		return userManagementDAO.deleteUser(user);
	}
	
	/**
	 * Method to List All users information.
	 * 
	 * @param user
	 * @return
	 */
	public List<User> listAllUsers() {
		return userManagementDAO.listAllUsers();
	}
}
