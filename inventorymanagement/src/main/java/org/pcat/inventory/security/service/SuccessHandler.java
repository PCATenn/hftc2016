package org.pcat.inventory.security.service;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.pcat.inventory.security.model.PcatUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(SuccessHandler.class);
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	private String determineUrl(Authentication authentication) {
		String returnUrl = null;
		boolean admin = false;
		boolean supervisor = false;
		boolean homeVisitor = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			logger.debug(String.format("contained authority is %s", authority.getAuthority()));
			if (authority.getAuthority().equals(PcatUserDetails.ROLE_ADMINISTRATOR)) {
				admin = true;
				break;
			}
			if (authority.getAuthority().equals(PcatUserDetails.ROLE_SUPERVISOR)) {
				supervisor = true;
			}
			if (authority.getAuthority().equals(PcatUserDetails.ROLE_HOME_VISITOR)) {
				homeVisitor = true;
			}
		}
		if (admin) {
			returnUrl = "/listAllInventories.jsp";
		} else {
			if (supervisor) {
				returnUrl = "/review-approvals.jsp";
			} else {
				if (homeVisitor) {
					returnUrl = "/request.jsp";
				}
			}
		}
		logger.info(String.format("role based url determination is %s", returnUrl));
		return returnUrl;
	}

	private void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String homeUrl = determineUrl(authentication);
		if (response.isCommitted()) {
			logger.debug(String.format("response already committed, not redirecting to %s", homeUrl));
			return;
		}
		redirectStrategy.sendRedirect(request, response, homeUrl);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

}
