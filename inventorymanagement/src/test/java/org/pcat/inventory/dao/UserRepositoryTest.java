package org.pcat.inventory.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcat.inventory.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:serviceContext.xml" })
public class UserRepositoryTest {
	public static final Logger logger = LoggerFactory.getLogger(UserRepositoryTest.class);
	@Autowired
	UserRepository repo;

	@Test
	public void userAllSelectTest() {
		User user = new User(null, "firstName", "testLastName", "testUserEmail@mailinator.com", "Home Visitor", "testSupervisor", "testSupervisor@mailinator.com", true);
		repo.save(user);
		repo.findAll().forEach(retrieveUser -> logger.info(retrieveUser.toString()));
	}

}
