package org.pcat.inventory.service;

import org.pcat.inventory.dao.UserDao;
import org.pcat.inventory.model.User;
import org.pcat.inventory.security.model.PcatUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		User user = userDao.getByEmailId(userEmail);
		if (user == null) {
			throw new UsernameNotFoundException(userEmail);
		}
		return new PcatUserDetails(user);
	}

}
