package org.pcat.inventory.model;

import org.springframework.security.core.GrantedAuthority;

public class PcatAuthority implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4597144518062380579L;
	private String authority;

	private PcatAuthority() {
		super();
	}

	public PcatAuthority(String authority) {
		this();
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

}
