package org.pcat.inventory.service;

import java.util.ArrayList;
import java.util.List;

import org.pcat.inventory.dao.UserDao;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.PcatPerson;
import org.pcat.inventory.model.Supervisor;
import org.pcat.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	private HomeVisitor buildHomeVisitor(PcatPerson user) {
		HomeVisitor hm = new HomeVisitor(user);
		return hm;
	}

	private Supervisor buildSupervisor(PcatPerson user) {
		return new Supervisor(user);
	}

	public void delete(PcatPerson user) {
		this.userDao.delete(user);
	}

	public HomeVisitor getHomeVisitor(int userId) {
		PcatPerson user = userDao.getById(userId);
		return buildHomeVisitor(user);
	}

	public HomeVisitor getHomeVisitorByEmailAddress(String emailAddress) {
		PcatPerson user = userDao.getByEmailId(emailAddress);
		return buildHomeVisitor(user);
	}

	public List<HomeVisitor> getHomeVisitorsFromSupervisorEmail(String supervisorEmail) {
		List<User> pcatPersons = userDao.getBySupervisorEmail(supervisorEmail);
		List<HomeVisitor> homeVisitors = new ArrayList<HomeVisitor>();
		pcatPersons.forEach(pc -> homeVisitors.add(buildHomeVisitor(pc)));
		return homeVisitors;
	}

	public Supervisor getSupervisor(int userId) {
		PcatPerson user = userDao.getById(userId);
		return buildSupervisor(user);
	}

	public List<User> listAllUsers() {
		return this.userDao.findAll();
	}

	public void save(User user) {
		this.userDao.saveOrUpdate(user);
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUser(Integer id) {
		return this.userDao.getById(id);
	}

}
