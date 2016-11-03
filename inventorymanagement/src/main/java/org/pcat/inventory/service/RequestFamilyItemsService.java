package org.pcat.inventory.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pcat.inventory.dao.InventoryDao;
import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.Inventory;
import org.pcat.inventory.model.RequestItem;
import org.pcat.inventory.model.RequestState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestFamilyItemsService {

	@Autowired
	private MailService mailService;
	@Autowired
	private HomeVisitorEmailRequestBO requestBO;
	@Autowired
	private InventoryBO inventoryBO;
	private InventoryDao inventoryDao;

	public RequestState requestItems(final String familyNumber, final List<RequestItem> requestItems,
			final HomeVisitor homeVisitor) {

		/* get inventory */
		updateRequestItemsWithInventory(requestItems);
		/* create family inventory records */
		/*
		 * create a map of inventory records to get the inventory items out of
		 * it to get the fam request right need to match the request items to
		 * the inventory records to create the fam request record
		 * 
		 * maybe create a utility object to hold the data?
		 * 
		 */
		List<FamilyInventory> familyInventory = new ArrayList<>();
		/* update inventory records */
		/* send email */
		final List<String> itemDescriptions = inventoryBO.getItemDescriptions(requestItems);
		final String toEmail = homeVisitor.getEmail();
		final String supervisorEmail = homeVisitor.getSupervisorEmail();
		final String subject = String.format(HomeVisitorEmailRequestBO.HOME_VISITOR_SUBJECT, familyNumber);
		final String firstname = homeVisitor.getFirstname();
		final String lastname = homeVisitor.getLastname();
		final String messageBody = requestBO.getMessageBody(firstname, lastname, itemDescriptions);
		mailService.sendMail(toEmail, supervisorEmail, subject, messageBody);
		return RequestState.PENDING;
	}

	private void updateRequestItemsWithInventory(final List<RequestItem> requestItems) {
		requestItems.forEach(requestItem -> updateRequestItemWithInventory(requestItem));
	}

	private void updateRequestItemWithInventory(RequestItem requestItem) {
		requestItem.setRequestInventory(inventoryDao.getById(requestItem.getId()));
	}

	public void setInventoryBusinessObject(InventoryBO inventoryBO) {
		this.inventoryBO = inventoryBO;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setRequestUtility(HomeVisitorEmailRequestBO requestBO) {
		this.requestBO = requestBO;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

}
