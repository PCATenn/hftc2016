package org.pcat.inventory.service;

import org.pcat.inventory.dao.UserDao;
import org.pcat.inventory.model.User;
import org.pcat.inventory.security.model.PcatUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PcatUserDetailsService implements UserDetailsService {
	@Autowired
	LoginService service;
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		User user = userDao.getByEmailId(userEmail);
		if (user == null) {
			throw new UsernameNotFoundException(userEmail);
		}
		return new PcatUserDetails(user);
	}

}
