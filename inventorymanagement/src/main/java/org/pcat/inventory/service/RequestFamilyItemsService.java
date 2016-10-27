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
	private HomeVisitorEmailRequestUtility requestUtility;
	@Autowired
	private InventoryBusinessObject inventoryBusinessObject;

	public RequestState requestItems(String familyNumber, List<RequestItem> requestItems,
			HomeVisitor homeVisitor) {
		List<String> itemDescriptions = inventoryBusinessObject.getItemDescriptions(requestItems);
		mailService.sendMail(homeVisitor.getEmail(), homeVisitor.getSupervisorEmail(),
				String.format(HomeVisitorEmailRequestUtility.HOME_VISITOR_SUBJECT, familyNumber),
				requestUtility.getMessageBody(homeVisitor.getFirstname(), homeVisitor.getLastname(), itemDescriptions));
		return RequestState.PENDING;
	}

	public void setInventoryBusinessObject(InventoryBusinessObject inventoryBusinessObject) {
		this.inventoryBusinessObject = inventoryBusinessObject;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	public void setRequestUtility(HomeVisitorEmailRequestUtility requestUtility) {
		this.requestUtility = requestUtility;
	}

}
