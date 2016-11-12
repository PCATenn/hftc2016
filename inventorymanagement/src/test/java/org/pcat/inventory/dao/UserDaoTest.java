package org.pcat.inventory.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcat.inventory.TestHelper;
import org.pcat.inventory.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Level;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:serviceContext.xml" })
@Transactional
public class UserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	private static TestHelper testHelper = new TestHelper();
	@Autowired
	private UserDao userDao;

	@BeforeClass
	public static void setup() {
		testHelper.saveCurrentRootLogging();
		testHelper.setRootTestLoggerLevel(Level.DEBUG);
	}

	@AfterClass
	public static void tearDown() {
		testHelper.resetSaveRootLoggerLevel();
	}

	@Test
	public void userCrudTest() {
		User user = new User(null, "testFirstNameGetUserByEmailTest", "testLastName",
				"testFirstNameGetUserByEmailTest@mailinator.com", "Supervisor", "testSupervisor",
				"testSupervisor@mailinator.com", true);
		userDao.saveOrUpdate(user);
		assertThat(userDao.getByEmailId("testFirstNameGetUserByEmailTest@mailinator.com").getFirstname(),
				equalTo("testFirstNameGetUserByEmailTest"));
		assertThat(userDao.delete(user), equalTo(true));
	}
}
