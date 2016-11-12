package org.pcat.inventory.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class InventoryDaoIntegrationTest {

	private static final Logger logger = LoggerFactory.getLogger(InventoryDaoIntegrationTest.class);
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
	public void getCollectionOfInventoryByCollectionOfIds() {
		List<Integer> createdIds = new ArrayList<>();
		for (int x = 0; x < 26; x++) {
			String nameAndDesc = String.format("Item %d", x);
			Inventory insertInv = new Inventory(null, nameAndDesc, nameAndDesc, 12, 2, "Nashville");
			inventoryDao.saveOrUpdate(insertInv);
			logger.debug(String.format("inventory created: %s", insertInv.toString()));
			createdIds.add(insertInv.getId());
		}
		Collection<Inventory> inventory = inventoryDao.getCollectionById(createdIds.subList(12, 18));

		inventory.forEach(invent -> logger.debug(String.format("collection inventory : %s", invent.toString())));
		List<String> prodNames = new ArrayList<>();
		for (int x = 12; x < 19; x++) {
			String prodName = String.format("Item %d", x);
			prodNames.add(prodName);
			logger.debug(String.format("testing for item name - %s", prodName));
		}
		for (Inventory item : inventory) {
			logger.debug(String.format("testing item name - %s", item.getProductName()));
			assertThat(item.getProductName(), isIn(prodNames));
		}
//		throw new RuntimeException("just to see if it rolls back");
	}

}
