package org.pcat.inventory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.pcat.inventory.model.PcatPerson;
import org.pcat.inventory.model.User;
import org.pcat.inventory.service.UserService;
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
	private UserService userService;

	@Autowired
	private MailSender mailSender;

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
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setSupervisor(request.getParameter("supervisor"));
		user.setSupervisorEmail(request.getParameter("supervisorEmail"));
		user.setEmail(request.getParameter("email"));
		user.setRole(request.getParameter("role"));
		userService.save(user);
		return new ModelAndView("success");
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
	 * Method to delete User Details into Database.
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request, Model model) {
		PcatPerson user = new User();
		user.setId(new Integer(request.getParameter("userId")));
		userService.delete(user);
		return new ModelAndView("success");
	}

	/**
	 * @return the mailSender
	 */
	public MailSender getMailSender() {
		return mailSender;
	}

	/**
	 * @return the userService
	 */
	public UserService getuserService() {
		return userService;
	}
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView getUser(HttpServletRequest request, Model model) {
		Integer id = new Integer(request.getParameter("id"));
		User user = userService.getUser(id);
		return new ModelAndView("update-user.jsp", "user", user);
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
		return userService.listAllUsers();
	}
	
	/**
	 * @param mailSender
	 *            the mailSender to set
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setuserService(UserService userService) {
		this.userService = userService;
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
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setSupervisor(request.getParameter("supervisor"));
		user.setSupervisorEmail(request.getParameter("supervisorEmail"));
		user.setEmail(request.getParameter("email"));
		user.setRole(request.getParameter("role"));
		userService.save(user);
		return new ModelAndView("success");
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
}
