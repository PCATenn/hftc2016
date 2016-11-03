package org.pcat.inventory.service;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pcat.inventory.TestHelper;
import org.pcat.inventory.dao.InventoryDao;
import org.pcat.inventory.model.Inventory;
import org.pcat.inventory.model.RequestItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class InventoryBOTest {
	private static final Logger logger = LoggerFactory.getLogger(InventoryBOTest.class);
	private static TestHelper helper = new TestHelper();

	@BeforeClass
	public static void setup() {
		helper.saveCurrentRootLogging();
		helper.setRootTestLoggerLevel(Level.DEBUG);
	}

	@AfterClass
	public static void tearDown() {
		helper.resetSaveRootLoggerLevel();
	}

	private InventoryBO inventoryBO = new InventoryBO();

	private Inventory createATestInventory(int i, int totalQuantity) {
		Inventory inv = new Inventory();
		inv.setProductDesc(String.format("Item %d", i));
		inv.setProductName(inv.getProductDesc());
		inv.setId(i);
		inv.setTotalInventory(totalQuantity);
		return inv;
	}

	@Test
	public void itemDescriptionsTest() {
		InventoryDao dao = mock(InventoryDao.class);
		inventoryBO.setInventoryDao(dao);
		Random qtyGenerator = new Random();
		for (int x = 1; x < 7; x++) {
			Inventory req = createATestInventory(x, qtyGenerator.nextInt(16) + 4);
			logger.debug(String.format("debug created inventory is %s", req.toString()));
			when(dao.getById(x)).thenReturn(req);
		}
		List<RequestItem> items = new ArrayList<RequestItem>();
		for (int x = 1; x < 7; x++) {
			RequestItem item = new RequestItem(x, 1, null);
			logger.debug(String.format("adding %s to items list ", item.toString()));
			items.add(item);
		}
		List<String> inventoryDescriptions = inventoryBO.getItemDescriptions(items);
		logger.debug("list is:" + inventoryDescriptions.toString());
		for (int x = 1; x < 7; x++) {
			logger.debug(String.format("inventoryDescriptions[%d] is: %s", x, inventoryDescriptions.get(x - 1)));
			Inventory inv = dao.getById(x);
			logger.debug(String.format("mock when given %d is returning %s", inv.getId(), inv.getProductDesc()));
			assertThat(inventoryDescriptions, hasItem(String.format("Item %d", x)));
		}
	}
}
