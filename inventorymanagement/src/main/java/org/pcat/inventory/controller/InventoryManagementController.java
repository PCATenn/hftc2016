package org.pcat.inventory.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.pcat.inventory.model.FamilyInventoryDisplayRequest;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.Inventory;
import org.pcat.inventory.model.Supervisor;
import org.pcat.inventory.security.model.PcatUserDetails;
import org.pcat.inventory.service.InventoryManagementService;
import org.pcat.inventory.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class InventoryManagementController {

	private static final Logger logger = LoggerFactory.getLogger(InventoryManagementController.class);

	@Autowired
	private InventoryManagementService inventoryManagementService;

	@Autowired
	private UserService userService;

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
		logger.info("/addInventory public ModelAndView addInventory(HttpServletRequest request, Model model)");
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
	public ModelAndView updateInventory(HttpServletRequest request, Model model) {
		logger.info("@RequestMapping(value = /updateInventory, method = RequestMethod.POST) "
				+ "	public ModelAndView updateUser(HttpServletRequest request, Model model");
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
	public ModelAndView deletInventory(HttpServletRequest request, Model model) {
		logger.info("@RequestMapping(value = /deleteInventory, method = RequestMethod.POST) "
				+ " public ModelAndView deleteUser(HttpServletRequest request, Model model)");
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
	@RequestMapping(value = "/listAvailableInventories")
	@ResponseBody
	public List<Inventory> listAvailableInventories(HttpServletRequest request, Model model) {
		logger.info("@RequestMapping(value = /listAllInventories) "
				+ " @ResponseBody 	public List<Inventory> listAvailableInventories(HttpServletRequest request, Model model)");

		return inventoryManagementService.listAllInventory().stream()
				.filter(inventory -> (inventory.getTotalInventory() - inventory.getReservedInventory() > 0))
				.collect(Collectors.toList());
	}

	@RequestMapping(value = "/listAllItems")
	@ResponseBody
	public List<Inventory> listAllItems(HttpServletRequest request, Model model) {
		logger.info("@RequestMapping(value = /listAllItems) "
				+ " @ResponseBody 	public List<Inventory> listAllItems(HttpServletRequest request, Model model)");

		return inventoryManagementService.listAllInventory();
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
		logger.info("@RequestMapping(value = /gotoComplete, method = RequestMethod.POST)	"
				+ "@ResponseBody	public ModelAndView gotoComplete(HttpServletRequest request, Model model)");
		Inventory inventory = new Inventory();
		inventory.setId(new Integer(request.getParameter("id")));
		inventory.setLocation(request.getParameter("location"));
		inventory.setProductName(request.getParameter("productName"));
		inventory.setProductDesc(request.getParameter("productDesc"));
		return new ModelAndView("complete-request.jsp", "inventory", inventory);
	}

	@RequestMapping(value = "/getInventoryItem", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getInventoryItem(HttpServletRequest request, Model model) {
		logger.info("@RequestMapping(value = /getInventoryItem, method = RequestMethod.POST)	"
				+ "@ResponseBody	public ModelAndView gotoComplete(HttpServletRequest request, Model model)");
		Integer id = new Integer(request.getParameter("id"));
		Inventory inventory = inventoryManagementService.getInventory(id);
		return new ModelAndView("update-item.jsp", "inventory", inventory);
	}

	/**
	 * Method to delete inventory Details into Database.
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listPendingRequests")
	@ResponseBody
	public List<FamilyInventoryDisplayRequest> listPendingRequests(HttpServletRequest request, Model model) {
		logger.info("	@RequestMapping(value = /listPendingRequests)	@ResponseBody	"
				+ "public List<FamilyInventoryDisplayRequest> listAllInventoryPending(HttpServletRequest request, Model model) ");
		if (request.isUserInRole(PcatUserDetails.ROLE_ADMINISTRATOR)) {
			return inventoryManagementService.listAllFamilyInventoryDataRequest();
		}
		PcatUserDetails pcatUserDetails = (PcatUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Supervisor supervisor = new Supervisor(pcatUserDetails.getUser());
		Map<Integer, HomeVisitor> managedHomeVisitors = userService
				.getHomeVisitorsFromSupervisorEmail(supervisor.getEmail()).stream()
				.collect(Collectors.toMap(hv -> hv.getId(), hv -> hv));
		List<FamilyInventoryDisplayRequest> inventoryList = inventoryManagementService
				.listAllFamilyInventoryDataRequest().stream()
				.filter(inventory -> doesTheUserManageHomeVisitor(inventory, managedHomeVisitors))
				.collect(Collectors.toList());
		if (logger.isDebugEnabled()) {
			inventoryList
					.forEach(famInv -> logger.debug(String.format("item sent to requestor %s", famInv.toString())));
		}
		return inventoryList;
	}

	private boolean doesTheUserManageHomeVisitor(FamilyInventoryDisplayRequest inventory,
			Map<Integer, HomeVisitor> homeVisitors) {
		return homeVisitors.containsKey(inventory.getRequestorId());
	}
}
