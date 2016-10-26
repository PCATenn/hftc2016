package org.pcat.inventory.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.pcat.inventory.model.RequestItem;

public class InventoryBusinessObjectTest {

	private InventoryBusinessObject inventoryBusinessObject = new InventoryBusinessObject();
	
	

	@Test
	public void itemDescriptionsTest() {
		List<RequestItem> items = new ArrayList<RequestItem>();
		items.add(new RequestItem(1, 1));
		items.add(new RequestItem(2, 1));
		items.add(new RequestItem(3, 1));
		items.add(new RequestItem(4, 1));
		items.add(new RequestItem(5, 1));
		items.add(new RequestItem(6, 1));

		assertThat(inventoryBusinessObject.getItemDescriptions(items), equalTo(new ArrayList<String>()));
	}
}
