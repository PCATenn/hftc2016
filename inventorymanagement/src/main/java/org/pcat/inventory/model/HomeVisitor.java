package org.pcat.inventory.model;

public class HomeVisitor {
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

}
