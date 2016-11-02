package org.pcat.inventory.model;

import java.io.Serializable;

public class RequestItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8020159033237893273L;
	private int quantity;
	private int id;

	public RequestItem() {
		super();
	}

	public RequestItem(int id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return String.format("Id:  %d and Qty: %d", id, quantity);
	}

}
