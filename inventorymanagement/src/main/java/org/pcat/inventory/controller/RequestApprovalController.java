package org.pcat.inventory.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.service.RequestApprovalService;
import org.pcat.inventory.service.RequestFamilyItemsService;
import org.pcat.inventory.service.UserService;
import org.pcat.inventory.model.RequestItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RequestApprovalController {

	@Autowired
	private RequestApprovalService requestApprovalService;
	@Autowired
	private RequestFamilyItemsService requestFamilyItemsService;
	@Autowired
	private UserService userService;

	/**
	 * @return the RequestApprovalService
	 */
	public RequestApprovalService getRequestApprovalService() {
		return requestApprovalService;
	}

	/**
	 * @param requestApprovalService
	 *            the RequestApprovalService to set
	 */
	public void setRequestApprovalService(RequestApprovalService requestApprovalService) {
		this.requestApprovalService = requestApprovalService;
	}

	/**
	 * This method approves requests and updates the database for approved
	 * inventory.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/requestApproval", method = RequestMethod.POST)
	public ModelAndView approveRequests(HttpServletRequest request, Model model) {
		int userId = Integer.valueOf(request.getParameter("userId"));
		int familyInventoryId = Integer.valueOf(request.getParameter("familyInventoryId"));
		requestApprovalService.approveRequests(userId, familyInventoryId);
		return new ModelAndView("confirm-approvals.jsp");
	}

	/**
	 * This method approves requests and updates the database for approved
	 * inventory.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/submitForRequestApproval", method = RequestMethod.POST)
	public ModelAndView submitRequests(HttpServletRequest request, Model model) {

		int userId = Integer.valueOf(request.getParameter("userId"));
		String familyId = request.getParameter("familyId");
		int inventoryId = Integer.valueOf(request.getParameter("inventoryId"));
		int quantity = 1;
		String requestQty = request.getParameter("quantity");
		
		if(requestQty != null && !requestQty.trim().isEmpty()) {
			quantity = Integer.valueOf(request.getParameter("quantity"));
		}
//		requestApprovalService.submitRequests(userId, familyId, inventoryId, quantity);
		List<RequestItem> requestItems = new ArrayList<>();
		requestItems.add(new RequestItem(inventoryId, quantity, null));
		HomeVisitor homeVisitor = userService.getHomeVisitor(userId);
		requestFamilyItemsService.requestItems(familyId, requestItems, homeVisitor);
		return new ModelAndView("confirm-request.jsp");
	}
}
