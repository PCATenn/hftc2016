package org.pcat.inventory.service;

import org.pcat.inventory.dao.LoginDAO;
import org.pcat.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	private LoginDAO loginDAO;

	/**
	 * @return the LoginDAO
	 */
	public LoginDAO getUserLoginDAO() {
		return loginDAO;
	}

	/**
	 * @param loginDAO
	 *            the LoginDAO to set
	 */
	public void setUserLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	/**
	 * @param email
	 * @return User : user if valid user
	 */
	public User validateUserLogin(String email)
	{
		return loginDAO.validateUserLogin(email);
	}

}
