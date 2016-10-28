package org.pcat.inventory.model;

public class RequestItem {

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
