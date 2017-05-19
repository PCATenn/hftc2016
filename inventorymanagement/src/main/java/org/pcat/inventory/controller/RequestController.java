package org.pcat.inventory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.service.RequestFamilyItemsService;
import org.pcat.inventory.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.pcat.inventory.model.RequestItem;
import org.pcat.inventory.model.Supervisor;
import org.pcat.inventory.security.model.PcatUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/request")
@RestController
public class RequestController {
	private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
	@Autowired
	private  RequestFamilyItemsService requestFamilyItemsService;
	@Autowired
	private  UserService userService;

	/**
	 * This method approves requests and updates the database for approved
	 * inventory.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping(path="/supervisorApproved")
	public ModelAndView approve(@RequestParam("familyInventoryId") int familyInventoryRequestId, Model model) {
		logger.info("@PostMapping(path=\"/supervisorApproved\")	"
				+ "public ModelAndView approve(@RequestParam(\"familyInventoryId\") int familyInventoryRequestId, Model model)");
		int userId = getUserId();
		logger.debug(String.format("userId %d is approving request id %d", userId, familyInventoryRequestId));
		Supervisor supervisor = userService.getSupervisor(userId);
		logger.debug(String.format("supervisor is %s", supervisor));
		requestFamilyItemsService.approveFamilyItems(supervisor, familyInventoryRequestId);
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
	@PostMapping(path="/submitForapproval")
	public ModelAndView submitRequests(HttpServletRequest request, Model model) {
		logger.info("@RequestMapping(value = /submitForapproval, method = RequestMethod.POST)	"
				+ "public ModelAndView submitRequests(HttpServletRequest request, Model model)");
		int userId = getUserId();
		String familyId = request.getParameter("familyId");
		int inventoryId = Integer.valueOf(request.getParameter("inventoryId"));
		int quantity = 1;
		String requestQty = request.getParameter("quantity");

		if (requestQty != null && !requestQty.trim().isEmpty()) {
			quantity = Integer.valueOf(request.getParameter("quantity"));
		}
		List<RequestItem> requestItems = new ArrayList<>();
		requestItems.add(new RequestItem(inventoryId, quantity, null));
	
		logger.debug(String.format("getHomeVisitor(%d)", userId));
		HomeVisitor homeVisitor = userService.getHomeVisitor(userId);
		logger.debug(String.format("requestItems(%s, %s, %s)", familyId, requestItems.toString(), homeVisitor.getLastName()));
		requestFamilyItemsService.requestItems(familyId, requestItems, homeVisitor);
		return new ModelAndView("request-submitted-confirmation.jsp");
	}
	@RequestMapping(value = "/makeRequest", method = RequestMethod.POST)
	public ModelAndView makeRequest(HttpServletRequest request, Model model) {
		logger.info("@RequestMapping(value = /makeRequest, method = RequestMethod.POST)	"
				+ "public ModelAndView makeRequest(HttpServletRequest request, Model model)");
		logger.debug(String.format("here is the model available:  %s", model.toString()));
		int userId = getUserId();
		String familyId = request.getParameter("familyId");
		int inventoryId = Integer.valueOf(request.getParameter("inventoryId"));
		int quantity = 1;
		String requestQty = request.getParameter("quantity");

		if (requestQty != null && !requestQty.trim().isEmpty()) {
			quantity = Integer.valueOf(request.getParameter("quantity"));
		}

	    model.addAttribute("familyId", familyId);
	    model.addAttribute("quantity", requestQty);
	    model.addAttribute("inventoryId", inventoryId);
	    model.addAttribute("productName", request.getParameter("productName"));
	    model.addAttribute("productDesc", request.getParameter("productDesc"));
	    model.addAttribute("location", request.getParameter("location"));
		return new ModelAndView("request-proposal.jsp");
	}

	private int getUserId() {
		PcatUserDetails userDetails =
				 (PcatUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		int userId = Integer.valueOf(userDetails.getUser().getId());
		return userId;
	}
}
