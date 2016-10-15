package org.pcat.inventory.service;

import java.util.List;

import org.pcat.inventory.dao.InventoryManagementDAO;
import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryManagementService {

	@Autowired
	private InventoryManagementDAO inventoryManagementDAO;

	/**
	 * @return the inventoryManagementDAO
	 */
	public InventoryManagementDAO getInventoryManagementDAO() {
		return inventoryManagementDAO;
	}

	/**
	 * @param inventoryManagementDAO
	 *            the inventoryManagementDAO to set
	 */
	public void setInventoryManagementDAO(InventoryManagementDAO inventoryManagementDAO) {
		this.inventoryManagementDAO = inventoryManagementDAO;
	}

	/**
	 * Method to save inventory information.
	 * 
	 * @param user
	 * @return
	 */
	public boolean SaveInventory(Inventory inventory) {
		return inventoryManagementDAO.saveInventory(inventory);
	}

	/**
	 * Method to update inventory information.
	 * 
	 * @param inventory
	 * @return
	 */
	public boolean updateInventory(Inventory inventory) {
		return inventoryManagementDAO.updateInventory(inventory);
	}

	/**
	 * Method to delete inventory information.
	 * 
	 * @param inventory
	 * @return
	 */
	public boolean deleteInventory(Inventory inventory) {
		return inventoryManagementDAO.deleteInventory(inventory);
	}
	
	public List<Inventory> listAllInventory(){
		return inventoryManagementDAO.listAllInventory();
		
	}
	
	public List<FamilyInventory> listAllFamilyInventory(){
		return inventoryManagementDAO.listAllFamilyInventory();
		
	}
}
