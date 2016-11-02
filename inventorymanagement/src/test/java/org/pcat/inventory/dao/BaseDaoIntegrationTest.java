package org.pcat.inventory.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.notNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcat.inventory.TestHelper;
import org.pcat.inventory.model.Inventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Level;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:serviceContext.xml" })
public class BaseDaoIntegrationTest {

	private static final Logger logger = LoggerFactory.getLogger(BaseDaoIntegrationTest.class);
	private static TestHelper helper = new TestHelper();
	
	@Autowired
	InventoryDao inventoryDao;

	@BeforeClass
	public static void setup() {
		helper.saveCurrentRootLogging();
		helper.setRootTestLoggerLevel(Level.INFO);
	}

	@AfterClass
	public static void tearDown() {
		helper.resetSaveRootLoggerLevel();
	}

	@Test
	@Transactional
	@Rollback(true)
	public void inventoryInsertAndGetTest() {
		Inventory insertInv = new Inventory(null, "Item 1", "Item 1", 12, 2, "Nashville");
		inventoryDao.saveOrUpdate(insertInv);
		logger.info("inserted inventory = " + insertInv.toString());
		Inventory inv = inventoryDao.getById(insertInv.getId());
		
		assertThat(inv, is(notNullValue()));
		
		assertThat(inv.getProductDesc(), equalTo("Item 1") );
	}

}
