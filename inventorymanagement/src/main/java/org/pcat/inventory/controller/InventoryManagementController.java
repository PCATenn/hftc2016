package org.pcat.inventory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.Inventory;
import org.pcat.inventory.service.InventoryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class InventoryManagementController {

	@Autowired
	private InventoryManagementService inventoryManagementService;

	/**
	 * @return the inventoryManagementService
	 */
	public InventoryManagementService getInventoryManagementService() {
		return inventoryManagementService;
	}

	/**
	 * @param inventoryManagementService
	 *            the inventoryManagementService to set
	 */
	public void setInventoryManagementService(InventoryManagementService inventoryManagementService) {
		this.inventoryManagementService = inventoryManagementService;
	}

	/**
	 * Method to save inventory Details into Database.
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addInventory", method = RequestMethod.POST)
	public ModelAndView addInventory(HttpServletRequest request, Model model) {
		Inventory inventory = new Inventory();
		inventory.setProductName(request.getParameter("productName"));
		inventory.setProductDesc(request.getParameter("productDesc"));
		inventory.setTotalInventory(new Integer(request.getParameter("totalInventory")));
		inventory.setReservedInventory(new Integer(request.getParameter("reservedInventory")));
		inventoryManagementService.SaveInventory(inventory);
		return new ModelAndView("success");
	}

	/**
	 * Method to update inventory Details into Database.
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateInventory", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, Model model) {
		Inventory inventory = new Inventory();
		inventory.setId(new Integer(request.getParameter("inventoryId")));
		inventory.setProductName(request.getParameter("productName"));
		inventory.setProductDesc(request.getParameter("productDesc"));
		inventory.setTotalInventory(new Integer(request.getParameter("totalInventory")));
		inventoryManagementService.updateInventory(inventory);
		return new ModelAndView("success");
	}

	/**
	 * Method to delete inventory Details into Database.
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteInventory", method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request, Model model) {
		Inventory inventory = new Inventory();
		inventory.setId(new Integer(request.getParameter("inventoryId")));
		inventoryManagementService.deleteInventory(inventory);
		return new ModelAndView("success");
	}

	/**
	 * Method to delete inventory Details into Database.
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listAllInventories")
	@ResponseBody
	public List<Inventory> listAllInventory(HttpServletRequest request, Model model) {
		List<Inventory> inventoryList = inventoryManagementService.listAllInventory();
		return inventoryList;
	}

	/**
	 * Method to delete inventory Details into Database.
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gotoComplete", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView gotoComplete(HttpServletRequest request, Model model) {
		Inventory inventory = new Inventory();
		inventory.setId(new Integer(request.getParameter("id")));
		inventory.setLocation(request.getParameter("location"));
		inventory.setProductName(request.getParameter("productName"));
		inventory.setProductDesc(request.getParameter("productDesc"));
		return new ModelAndView("complete-request.jsp", "inventory", inventory);
	}

	/**
	 * Method to delete inventory Details into Database.
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listAllInventoriesPending")
	@ResponseBody
	public List<FamilyInventory> listAllInventoryPending(HttpServletRequest request, Model model) {
		List<FamilyInventory> inventoryList = inventoryManagementService.listAllFamilyInventory();
		return inventoryList;
	}
}
