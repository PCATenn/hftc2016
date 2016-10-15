package org.pcat.inventory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.pcat.inventory.model.User;
import org.pcat.inventory.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserManagementController {

	@Autowired
	private UserManagementService userManagementService;

	@Autowired
	private MailSender mailSender;

	/**
	 * @return the userManagementService
	 */
	public UserManagementService getUserManagementService() {
		return userManagementService;
	}

	/**
	 * @param userManagementService
	 *            the userManagementService to set
	 */
	public void setUserManagementService(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	/**
	 * @return the mailSender
	 */
	public MailSender getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender
	 *            the mailSender to set
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Method to save User Details into Database.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUserPage")
	public ModelAndView addUserPage(HttpServletRequest request, Model model) {
		return new ModelAndView("add-user.jsp");
	}
	
	/**
	 * Method to save User Details into Database.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateUserPage", method = RequestMethod.POST)
	public ModelAndView updateUserPage(HttpServletRequest request, Model model) {
		return new ModelAndView("update-user.jsp");
	}
	
	/**
	 * Method to save User Details into Database.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, Model model) {
		User user = new User();
		user.setFirstname(request.getParameter("firstName"));
		user.setLastname(request.getParameter("lastName"));
		user.setSupervisor(request.getParameter("supervisor"));
		user.setSupervisoremail(request.getParameter("supervisorEmail"));
		user.setEmail(request.getParameter("email"));
		user.setRole(request.getParameter("role"));
		userManagementService.saveUser(user);
		// SimpleMailMessage mailMessage = new SimpleMailMessage();
		// mailMessage.setFrom("admin@pcat.org");
		// mailMessage.setTo(user.getSupervisoremail());
		// mailMessage.setSubject(
		// "User + " + user.getFirstname() + " " + user.getLastname() + " added
		// in Inventory System!");
		// mailSender.send(mailMessage);
		return new ModelAndView("success");
	}

	/**
	 * Method to update User Details into Database.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, Model model) {
		User user = new User();
		user.setId(new Integer(request.getParameter("userId")));
		user.setFirstname(request.getParameter("firstName"));
		user.setLastname(request.getParameter("lastName"));
		user.setSupervisor(request.getParameter("supervisor"));
		user.setSupervisoremail(request.getParameter("supervisorEmail"));
		user.setEmail(request.getParameter("email"));
		user.setRole(request.getParameter("role"));
		userManagementService.updateUser(user);
		// SimpleMailMessage mailMessage = new SimpleMailMessage();
		// mailMessage.setFrom("admin@pcat.org");
		// mailMessage.setTo(user.getSupervisoremail());
		// mailMessage.setSubject(
		// "User + " + user.getFirstname() + " " + user.getLastname() + "
		// updated in Inventory System!");
		// mailSender.send(mailMessage);
		return new ModelAndView("success");
	}

	/**
	 * Method to delete User Details into Database.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request, Model model) {
		User user = new User();
		user.setId(new Integer(request.getParameter("userId")));
		userManagementService.deleteUser(user);
		// SimpleMailMessage mailMessage = new SimpleMailMessage();
		// mailMessage.setFrom("admin@pcat.org");
		// mailMessage.setTo(user.getSupervisoremail());
		// mailMessage.setSubject(
		// "User + " + user.getId() + " deleted to the Inventory System!");
		// mailSender.send(mailMessage);
		return new ModelAndView("success");
	}

	/**
	 * Method to delete User Details into Database.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/listAllUsers", method = RequestMethod.GET)
	@ResponseBody
	public List<User> listAllUsers(HttpServletRequest request, Model model) {
		return userManagementService.listAllUsers();
	}
}
