package org.pcat.inventory.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.pcat.inventory.dao.FamilyInventoryDao;
import org.pcat.inventory.dao.InventoryDao;
import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.Inventory;
import org.pcat.inventory.model.RequestItem;
import org.pcat.inventory.model.RequestState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RequestFamilyItemsService {

	@Autowired
	private MailService mailService;
	@Autowired
	private HomeVisitorEmailRequestBO requestBO;
	@Autowired
	private InventoryBO inventoryBO;
	@Autowired
	private InventoryDao inventoryDao;
	@Autowired
	private FamilyInventoryDao familyInventoryDao;

	private void createFamilyInventory(final RequestItem requestItem, String familyNumber, HomeVisitor homeVisitor) {
		FamilyInventory item = new FamilyInventory(null, familyNumber, "Pending", requestItem.getQuantity(),
				new Timestamp(Instant.now().getEpochSecond()), requestItem.getRequestInventory().getId());
		familyInventoryDao.saveOrUpdate(item);
	}

	public FamilyInventoryDao getFamilyInventoryDao() {
		return familyInventoryDao;
	}

	@Transactional
	public RequestState requestItems(final String familyNumber, final List<RequestItem> requestItems,
			final HomeVisitor homeVisitor) {

		/* get inventory, update inventory */
		processRequestAndInventories(requestItems);
		/* create family inventory records */
		updateFamilyInventory(requestItems, familyNumber, homeVisitor);
		/* send the email to the supervisor */
		sendRequestEmail(familyNumber, requestItems, homeVisitor);
		return RequestState.PENDING;
	}

	private void sendRequestEmail(final String familyNumber, final List<RequestItem> requestItems,
			final HomeVisitor homeVisitor) {
		final List<String> itemDescriptions = inventoryBO.getItemDescriptions(requestItems);
		final String fromEmail = homeVisitor.getEmail();
		final String ccEmail = fromEmail;
		final String supervisorEmail = homeVisitor.getSupervisorEmail();
		final String subject = String.format(HomeVisitorEmailRequestBO.HOME_VISITOR_SUBJECT, familyNumber);
		final String firstname = homeVisitor.getFirstname();
		final String lastname = homeVisitor.getLastname();
		final String messageBody = requestBO.getMessageBody(firstname, lastname, itemDescriptions);
		mailService.sendMail(fromEmail, supervisorEmail, ccEmail, subject, messageBody);
	}

	public void setFamilyInventoryDao(FamilyInventoryDao familyInventoryDao) {
		this.familyInventoryDao = familyInventoryDao;
	}

	public void setInventoryBusinessObject(InventoryBO inventoryBO) {
		this.inventoryBO = inventoryBO;
	}

	public void setInventoryDao(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setRequestUtility(HomeVisitorEmailRequestBO requestBO) {
		this.requestBO = requestBO;
	}

	private void updateFamilyInventory(final Iterable<RequestItem> requestItems, String familyNumber,
			HomeVisitor homeVisitor) {
		requestItems.forEach(requestItem -> createFamilyInventory(requestItem, familyNumber, homeVisitor));

	}

	private void processRequestAndInventories(final List<RequestItem> requestItems) {
		requestItems.forEach(requestItem -> updateInventoryWithRequest(requestItem));
	}

	private void updateInventoryWithRequest(RequestItem requestItem) {
		Inventory inventory = inventoryDao.getById(requestItem.getId());
		final Integer totalInventory = inventory.getTotalInventory();
		Integer reservedInventory = inventory.getReservedInventory();
		final int requestQuantity = requestItem.getQuantity();
		if (totalInventory > reservedInventory + requestQuantity) {
			inventory.setReservedInventory(reservedInventory + requestQuantity);
			inventoryDao.saveOrUpdate(inventory);
		} else {
			throw new RuntimeException(String.format(
					"Request qty of %d plus already reserved qty of %d is greater than the available qty of %d",
					reservedInventory, requestQuantity, totalInventory));
		}
		requestItem.setRequestInventory(inventory);
	}

}
