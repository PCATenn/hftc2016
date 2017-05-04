package org.pcat.inventory.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.PcatAuthority;
import org.pcat.inventory.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public class PcatUserDetails implements UserDetails {

	private static final long serialVersionUID = 6719074966324925290L;
	private User user;
	public static final String HOME_VISITOR = "Home Visitor";
	public static final String SUPERVISOR = "Supervisor";
	public static final String ADMINISTRATOR = "Administrator";

	public static final String ROLE_HOME_VISITOR = "ROLE_HOME_VISITOR";
	public static final String ROLE_SUPERVISOR = "ROLE_SUPERVISOR";
	public static final String ROLE_ADMINISTRATOR = "ROLE_ADMINISTRATOR";

	private Collection<PcatAuthority> roles;

	public PcatUserDetails(User user) {
		List<PcatAuthority> roles = new ArrayList<PcatAuthority>();
		this.user = user;
		String userRole = user.getRole();
		if (!(HOME_VISITOR.equals(userRole) || SUPERVISOR.equals(userRole) || ADMINISTRATOR.equals(userRole))) {
			throw new RuntimeException(String.format("User %s does not have a valid role.  Contains role: %s",
					user.getEmail(), user.getRole()));
		}
		if (HOME_VISITOR.equals(userRole) || SUPERVISOR.equals(userRole) || ADMINISTRATOR.equals(userRole)) {
			roles.add(new PcatAuthority(ROLE_HOME_VISITOR));
		}
		if (SUPERVISOR.equals(userRole) || ADMINISTRATOR.equals(userRole)) {
			roles.add(new PcatAuthority(ROLE_SUPERVISOR));
		}
		if (ADMINISTRATOR.equals(userRole)) {
			roles.add(new PcatAuthority(ROLE_ADMINISTRATOR));
		}
		this.roles = roles;
	}

	@Override
	public Collection<PcatAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return " ";
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getIsactive();
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getIsactive();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return user.getIsactive();
	}

	@Override
	public boolean isEnabled() {
		return user.getIsactive();
	}

	public User getUser() {
		return user;
	}

}
