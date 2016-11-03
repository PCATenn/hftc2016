package org.pcat.inventory.model;

import java.io.Serializable;

public class RequestItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8020159033237893273L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private int quantity;
	private int id;

	private Inventory requestInventory;

	public RequestItem() {
		super();
	}

	public RequestItem(int id, int quantity, Inventory requestInventory) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.requestInventory = requestInventory;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RequestItem)) {
			return false;
		}
		RequestItem other = (RequestItem) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	public int getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public Inventory getRequestInventory() {
		return requestInventory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setRequestInventory(Inventory requestInventory) {
		this.requestInventory = requestInventory;
	}

	@Override
	public String toString() {
		return String.format("Id:  %d and Qty: %d", id, quantity);
	}

}
