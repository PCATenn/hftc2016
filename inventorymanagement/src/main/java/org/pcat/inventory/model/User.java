package org.pcat.inventory.model;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private String firstname;
	private String supervisor;
	private String supervisoremail;
	private String email;
	private String role;
	private String lastname;
	private String isactive;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(String firstname, String supervisor, String email, String role, String lastname, String isactive) {
		this.firstname = firstname;
		this.supervisor = supervisor;
		this.email = email;
		this.role = role;
		this.lastname = lastname;
		this.isactive = isactive;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSupervisor() {
		return this.supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	/**
	 * @return the supervisoremail
	 */
	public String getSupervisoremail() {
		return supervisoremail;
	}

	/**
	 * @param supervisoremail
	 *            the supervisoremail to set
	 */
	public void setSupervisoremail(String supervisoremail) {
		this.supervisoremail = supervisoremail;
	}

}