package org.pcat.inventory.model;

public class RequestItem {

	private int quantity;
	private long id;

	public RequestItem() {
		super();
	}
	public RequestItem(long id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}
	public long getId() {
		return id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
