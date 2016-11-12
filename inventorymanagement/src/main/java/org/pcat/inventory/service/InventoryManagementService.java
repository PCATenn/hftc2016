package org.pcat.inventory.service;

import java.util.List;

import org.pcat.inventory.dao.InventoryDao;
import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryManagementService {

	@Autowired
	private InventoryDao inventoryDao;

	/**
	 * @return the inventoryManagementDAO
	 */
	public InventoryDao getInventoryManagementDAO() {
		return inventoryDao;
	}

	/**
	 * @param inventoryDao
	 *            the inventoryManagementDAO to set
	 */
	public void setInventoryManagementDAO(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	/**
	 * Method to save inventory information.
	 * 
	 * @param user
	 * @return
	 */
	public boolean SaveInventory(Inventory inventory) {
		return inventoryDao.saveInventory(inventory);
	}

	/**
	 * Method to update inventory information.
	 * 
	 * @param inventory
	 * @return
	 */
	public boolean updateInventory(Inventory inventory) {
		return inventoryDao.updateInventory(inventory);
	}

	/**
	 * Method to delete inventory information.
	 * 
	 * @param inventory
	 * @return
	 */
	public boolean deleteInventory(Inventory inventory) {
		return inventoryDao.delete(inventory);
	}
	
	public List<Inventory> listAllInventory(){
		return inventoryDao.listAllInventory();
		
	}
	
	public List<FamilyInventory> listAllFamilyInventory(){
		return inventoryDao.listAllFamilyInventory();
		
	}
}
