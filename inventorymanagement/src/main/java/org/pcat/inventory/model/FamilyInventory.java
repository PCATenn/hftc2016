package org.pcat.inventory.model;

import java.sql.Timestamp;

/**
 * FamilyInventory entity. @author MyEclipse Persistence Tools
 */

public class FamilyInventory implements java.io.Serializable {
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

	public FamilyInventory() {
		super();
	}
	// Fields

	public FamilyInventory(Integer id, String familyId, String status, Integer quantity, Timestamp localDateTime,
			Integer inventoryId) {
		super();
		this.id = id;
		this.familyId = familyId;
		this.status = status;
		this.quantity = quantity;
		this.requestedDate = localDateTime;
		this.inventoryId = inventoryId;
	}

	// Property accessors

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof FamilyInventory)) {
			return false;
		}
		FamilyInventory other = (FamilyInventory) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public String getFamilyId() {
		return this.familyId;
	}

	public Integer getId() {
		return this.id;
	}

	public Integer getInventoryId() {
		return inventoryId;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	public Timestamp getRequestedDate() {
		return this.requestedDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setRequestedDate(Timestamp requestedDate) {
		this.requestedDate = requestedDate;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}