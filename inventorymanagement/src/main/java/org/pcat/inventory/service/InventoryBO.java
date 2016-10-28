package org.pcat.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.pcat.inventory.dao.InventoryDao;
import org.pcat.inventory.model.Inventory;
import org.pcat.inventory.model.RequestItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryBO {

	@Autowired
	private InventoryDao inventoryDAO;

	public InventoryBO() {
		super();
	}

	public List<String> getItemDescriptions(List<RequestItem> requestItems) {
		List<String> inventories = new ArrayList<String>();
		requestItems.forEach(item -> {
			Inventory inventory = inventoryDAO.getById(item.getId());
			inventories.add(inventory.getProductName());
		});
		return inventories;
	}

	public void setInventoryDao(InventoryDao dao) {
		this.inventoryDAO = dao;

	}

}
