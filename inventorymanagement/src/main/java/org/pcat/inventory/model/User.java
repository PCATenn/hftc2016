package org.pcat.inventory.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4241272990550941600L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	public String getEmail() {
		return this.email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public Integer getId() {
		return this.id;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public String getLastname() {
		return this.lastname;
	}

	public String getRole() {
		return this.role;
	}

	public String getSupervisor() {
		return this.supervisor;
	}

	/**
	 * @return the supervisoremail
	 */
	public String getSupervisoremail() {
		return supervisoremail;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	/**
	 * @param supervisoremail
	 *            the supervisoremail to set
	 */
	public void setSupervisoremail(String supervisoremail) {
		this.supervisoremail = supervisoremail;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, firstname='%s', lastname='%s', email='%s', supervisor='%s']", this.id,
				this.firstname, this.lastname, this.email, this.supervisor);
	}
}