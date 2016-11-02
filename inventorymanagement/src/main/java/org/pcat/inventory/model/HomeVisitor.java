package org.pcat.inventory.model;

import java.io.Serializable;

public class HomeVisitor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7654600580730325730L;
	private String firstname;
	private String lastname;
	private String email;
	private String supervisorEmail;

	public HomeVisitor() {
		super();
	}

	public HomeVisitor(String firstname, String lastname, String email, String supervisorEmail) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.supervisorEmail = supervisorEmail;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getSupervisorEmail() {
		return supervisorEmail;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setSupervisorEmail(String supervisorEmail) {
		this.supervisorEmail = supervisorEmail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof HomeVisitor)) {
			return false;
		}
		HomeVisitor other = (HomeVisitor) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		return true;
	}

}
