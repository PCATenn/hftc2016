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
	private String firstName;
	private String supervisor;
	private String supervisorEmail;
	private String email;
	private String role;
	private String lastName;
	private boolean isActive;

	public User() {
		super();
	}

	public User(Integer id, String firstName, String lastName, String email, String role, String supervisor,
			String supervisorEmail, boolean isActive) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.supervisor = supervisor;
		this.supervisorEmail = supervisorEmail;
		this.email = email;
		this.role = role;
		this.lastName = lastName;
		this.isActive = isActive;
	}

	public String getEmail() {
		return this.email;
	}

	public String getFirstname() {
		return this.firstName;
	}

	public Integer getId() {
		return this.id;
	}

	public boolean getIsactive() {
		return this.isActive;
	}

	public String getLastname() {
		return this.lastName;
	}

	public String getRole() {
		return this.role;
	}

	public String getSupervisor() {
		return this.supervisor;
	}

	public String getSupervisoremail() {
		return supervisorEmail;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIsactive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setLastname(String lastname) {
		this.lastName = lastname;
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
		this.supervisorEmail = supervisoremail;
	}

	@Override
	public String toString() {
		return String.format("User[id=%d, firstname='%s', lastname='%s', email='%s', supervisor='%s']", this.id,
				this.firstName, this.lastName, this.email, this.supervisor, this.isActive);
	}
}