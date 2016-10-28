package org.pcat.inventory.model;

/**
 * Inventory entity. @author MyEclipse Persistence Tools
 */

public class Inventory implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 2789161531642080248L;
	private Integer id;
	private String productName;
	private String productDesc;
	private Integer totalInventory;
	private Integer reservedInventory;
	private String location;

	public Inventory(Integer id, String productName, String productDesc, Integer totalInventory,
			Integer reservedInventory, String location) {
		super();
		this.id = id;
		this.productName = productName;
		this.productDesc = productDesc;
		this.totalInventory = totalInventory;
		this.reservedInventory = reservedInventory;
		this.location = location;
	}

	public Inventory() {
		super();
	}

	public Integer getId() {
		return this.id;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	public Integer getReservedInventory() {
		return this.reservedInventory;
	}

	public Integer getTotalInventory() {
		return this.totalInventory;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param productDesc
	 *            the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setReservedInventory(Integer reservedInventory) {
		this.reservedInventory = reservedInventory;
	}

	public void setTotalInventory(Integer totalInventory) {
		this.totalInventory = totalInventory;
	}

	@Override
	public String toString() {
		return String.format(
				"Id is: %d, Product Name: %s, Product Description: %s, Total Inventory: %d, Reserved Inventory: %d Location is: %s",
				id, productName, productDesc, totalInventory, reservedInventory, location);
	}

}