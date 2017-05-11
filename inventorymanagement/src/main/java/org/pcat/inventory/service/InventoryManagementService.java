package org.pcat.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.pcat.inventory.dao.FamilyInventoryDao;
import org.pcat.inventory.dao.FamilyInventoryDisplayRequestDao;
import org.pcat.inventory.dao.InventoryDao;
import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.FamilyInventoryDisplayRequest;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryManagementService {

	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private FamilyInventoryDao familyInventoryDao;
	@Autowired
	private FamilyInventoryDisplayRequestDao familyInventoryDisplayRequestDao;
	@Autowired
	private UserService userService;

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
	 */
	public void updateInventory(Inventory inventory) {
		 inventoryDao.update(inventory);
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

	public List<Inventory> listAllInventory() {
		return inventoryDao.listAll();

	}

	public List<FamilyInventory> listAllFamilyInventory() {
		return familyInventoryDao.listAll();

	}

	public List<FamilyInventoryDisplayRequest> listAllFamilyInventoryDataRequest() {
		return familyInventoryDisplayRequestDao.findAll();
	}

	public List<FamilyInventoryDisplayRequest> listAllFamilyInventoryDataRequestForSupervisor(
			final String supervisorEmail) {
		List<HomeVisitor> homeVisitors = userService.getHomeVisitorsFromSupervisorEmail(supervisorEmail);
		List<Integer> homeVisitorIds = new ArrayList<Integer>();
		homeVisitors.forEach(hv -> homeVisitorIds.add(hv.getId()));
		return familyInventoryDisplayRequestDao.findAllForIds(homeVisitorIds);
	}

	public Inventory getInventory(Integer id) {
		return inventoryDao.getById(id);
	}
}
