package org.pcat.inventory.service;

import java.util.List;

import org.pcat.inventory.model.HomeVisitor;
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

	public RequestState requestItems(final String familyNumber, final List<RequestItem> requestItems,
			final HomeVisitor homeVisitor) {
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

	public void setInventoryBusinessObject(InventoryBO inventoryBO) {
		this.inventoryBO = inventoryBO;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setRequestUtility(HomeVisitorEmailRequestBO requestBO) {
		this.requestBO = requestBO;
	}

}
