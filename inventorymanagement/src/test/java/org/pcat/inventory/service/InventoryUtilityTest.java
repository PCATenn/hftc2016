package org.pcat.inventory.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.RequestItem;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InventoryUtilityTest {

	@Test
	public void itemDescriptionsTest() {
		List<RequestItem> items = new ArrayList<RequestItem>();
		items.add(new RequestItem(1, 1));
		items.add(new RequestItem(2, 1));
		items.add(new RequestItem(3, 1));
		items.add(new RequestItem(4, 1));
		items.add(new RequestItem(5, 1));
		items.add(new RequestItem(6, 1));
	}
}
