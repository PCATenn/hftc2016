package org.pcat.inventory.service;

import org.pcat.inventory.dao.RequestApprovalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestApprovalService {

	@Autowired
	private RequestApprovalDAO requestApprovalDAO;

	/**
	 * @return the inventoryManagementDAO
	 */
	public RequestApprovalDAO getUserLoginDAO() {
		return requestApprovalDAO;
	}

	/**
	 * @param inventoryManagementDAO
	 *            the inventoryManagementDAO to set
	 */
	public void setUserLoginDAO(RequestApprovalDAO requestApprovalDAO) {
		this.requestApprovalDAO = requestApprovalDAO;
	}

	public boolean approveRequests(int userId, int familyInventoryId) {
		return requestApprovalDAO.approveRequests(userId, familyInventoryId);
	}

	public boolean submitRequests(int userId, String familyId, int inventoryId, int quantity) {
		return requestApprovalDAO.submitRequests(userId, familyId, inventoryId, quantity);
	}

}
