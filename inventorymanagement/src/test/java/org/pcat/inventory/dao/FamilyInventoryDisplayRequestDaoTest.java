package org.pcat.inventory.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcat.inventory.TestHelper;
import org.pcat.inventory.model.FamilyInventoryDisplayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Level;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:serviceContext.xml" })
@Transactional
public class FamilyInventoryDisplayRequestDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
	private static TestHelper testHelper = new TestHelper();
	@Autowired
	private FamilyInventoryDisplayRequestDao familyInventoryDisplayRequestDao;
	@Autowired
	private FamilyInventoryDao familyInventoryDao;
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
	public void readTest() {

		List<FamilyInventoryDisplayRequest> requests = familyInventoryDisplayRequestDao.findAll();

		List<Integer> ids = new ArrayList<Integer>();
		ids.add(new Integer(42));
		requests = familyInventoryDisplayRequestDao.findAllForIds(ids);

	}

}
