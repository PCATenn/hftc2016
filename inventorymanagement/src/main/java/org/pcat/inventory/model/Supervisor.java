package org.pcat.inventory.model;

import java.io.Serializable;

public class Supervisor implements PcatPerson, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4929537872579241867L;
	private PcatPerson pcatPerson;

	public Supervisor() {
		super();
		pcatPerson = new User();
	}

	public Supervisor(PcatPerson pcatPerson) {
		this();
		this.pcatPerson = pcatPerson;
	}

	public Supervisor(String firstName, String lastName, String email, String supervisor, String supervisorEmail) {
		this(new User(null, firstName, lastName, email, "Supervisor", supervisor, supervisorEmail, true));
	}

	public String getEmail() {
		return pcatPerson.getEmail();
	}

	public String getFirstName() {
		return pcatPerson.getFirstName();
	}

	public Integer getId() {
		return pcatPerson.getId();
	}

	public String getLastName() {
		return pcatPerson.getLastName();
	}

	public String getSupervisor() {
		return pcatPerson.getSupervisor();
	}

	public String getSupervisorEmail() {
		return pcatPerson.getSupervisorEmail();
	}

	public void setEmail(String email) {
		pcatPerson.setEmail(email);
	}

	public void setFirstName(String firstName) {
		pcatPerson.setFirstName(firstName);
	}

	public void setId(Integer id) {
		pcatPerson.setId(id);
	}

	public void setLastName(String lastname) {
		pcatPerson.setLastName(lastname);
	}

	public void setSupervisor(String supervisor) {
		pcatPerson.setSupervisor(supervisor);
	}

	public void setSupervisorEmail(String supervisoremail) {
		pcatPerson.setSupervisorEmail(supervisoremail);
	}

	public String toString() {
		return pcatPerson.toString();
	}

}
