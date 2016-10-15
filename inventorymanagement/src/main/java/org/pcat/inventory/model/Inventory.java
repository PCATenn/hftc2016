package org.pcat.inventory.model;

/**
 * Inventory entity. @author MyEclipse Persistence Tools
 */

public class Inventory implements java.io.Serializable {

	// Fields

	private Integer id;
	private String productName;
	private String productDesc;
	private Integer totalInventory;
	private Integer reservedInventory;
	// private FamilyInventory familyInventory;
	private String location;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotalInventory() {
		return this.totalInventory;
	}

	public void setTotalInventory(Integer totalInventory) {
		this.totalInventory = totalInventory;
	}

	public Integer getReservedInventory() {
		return this.reservedInventory;
	}

	public void setReservedInventory(Integer reservedInventory) {
		this.reservedInventory = reservedInventory;
	}


//	public FamilyInventory getFamilyInventory() {
//		return this.familyInventory;
//	}
//
//	public void setFamilyInventory(FamilyInventory familyInventory) {
//		this.familyInventory = familyInventory;
//	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the productDesc
	 */
	public String getProductDesc() {
		return productDesc;
	}

	/**
	 * @param productDesc
	 *            the productDesc to set
	 */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

}