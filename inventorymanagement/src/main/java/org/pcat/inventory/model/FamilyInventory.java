package org.pcat.inventory.model;

import java.sql.Timestamp;

/**
 * FamilyInventory entity. @author MyEclipse Persistence Tools
 */

public class FamilyInventory implements java.io.Serializable {

	// Fields

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String familyId;
	private String status;
	private Integer quantity;
	private Timestamp requestedDate;
	private Integer inventoryId;

	// Property accessors

	public Integer getInventoryId() {
	    return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
	    this.inventoryId = inventoryId;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFamilyId() {
		return this.familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Timestamp getRequestedDate() {
		return this.requestedDate;
	}

	public void setRequestedDate(Timestamp requestedDate) {
		this.requestedDate = requestedDate;
	}



	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}