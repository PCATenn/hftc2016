package org.pcat.inventory.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pcat.inventory.TestHelper;
import org.pcat.inventory.dao.UserDao;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.User;

public class UserServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
	private static TestHelper helper = new TestHelper();
	private static UserService userService = new UserService();

	@BeforeClass
	public static void setup() {
		helper.saveCurrentRootLogging();
		helper.setRootTestLoggerLevel(Level.DEBUG);
	}

	@AfterClass
	public static void tearDown() {
		helper.resetSaveRootLoggerLevel();
	}

	@Test
	public void homeVisitorTest() {
		UserDao mockUserDao = mock(UserDao.class);
		userService.setUserDao(mockUserDao);
		when(mockUserDao.getByEmailId("testUser@mailinator.com"))
				.thenReturn(new User(1, "testFirstName", "testLastName", "testUser@mailinator.com", "Home Visitor",
						"test Supervisor", "supervisorEmail@mailinator.com", true));
		HomeVisitor hv = userService.getHomeVisitorByEmailAddress("testUser@mailinator.com");
		assertThat(hv.getFirstname(), equalTo("testFirstName"));
		assertThat(hv.getLastname(), equalTo("testLastName"));
		assertThat(hv.getSupervisorEmail(), equalTo("supervisorEmail@mailinator.com"));
		verify(mockUserDao).getByEmailId("testUser@mailinator.com");
	}

}
