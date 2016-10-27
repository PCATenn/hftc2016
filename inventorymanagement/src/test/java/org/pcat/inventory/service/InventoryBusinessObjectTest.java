package org.pcat.inventory.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.pcat.inventory.dao.InventoryDao;
import org.pcat.inventory.model.Inventory;
import org.pcat.inventory.model.RequestItem;

public class InventoryBusinessObjectTest {

	private InventoryBusinessObject inventoryBusinessObject = new InventoryBusinessObject();

	@Test
	public void itemDescriptionsTest() {
		InventoryDao dao = mock(InventoryDao.class);
		inventoryBusinessObject.setInventoryDao(dao);
		for (int x = 1; x < 7; x++) {
			when(dao.getById(Long.valueOf(x))).thenReturn(createATestInventory(x));
		}
		List<RequestItem> items = new ArrayList<RequestItem>();
		for (int x = 1; x < 7; x++) {
			items.add(new RequestItem(x, 1));
		}
		List<String> inventoryDescriptions = inventoryBusinessObject.getItemDescriptions(items);
		for(int x = 1; x < 7; x++) {
			assert(inventoryDescriptions.contains(String.format("Item %d", x)));
		}
	}

	private Inventory createATestInventory(int i) {
		Inventory inv = new Inventory();
		inv.setProductDesc(String.format("Item %d", i));
		return inv;

	}
}
