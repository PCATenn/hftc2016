package org.pcat.inventory.dao;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Repository;

@Repository
public class RequestApprovalDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private MailSender mailSender;

	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
	 * This method approves the request and updates FamilyInventory and
	 * Inventory repositories and also sends the notification to homevisitor who
	 * requested this order.
	 *
	 * @param userId
	 *            : homevisitor id
	 * @param familyId
	 *            : familyId
	 * @param inventoryId
	 * @param familyInventoryId
	 * @return
	 */
	public boolean approveRequests(int userId, int familyInventoryId) {
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(FamilyInventory.class);
			criteria.add(Restrictions.eq("id", familyInventoryId));
			FamilyInventory familyInventory = (FamilyInventory) criteria.list().get(0);
			familyInventory.setStatus("Approved");
			session.update(familyInventory);
			tx.commit();
			// sendNotification(userId, familyInventory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * This method sends notification to homevisitor who made the request for
	 * the inventory.
	 *
	 * @param userId
	 * @param familyInventory
	 * @return
	 */
	private boolean sendNotification(int userId, FamilyInventory familyInventory) {
		boolean completedApproval = false;
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Restrictions.eq("id", userId));
			User user = (User) criteria.list().get(0);

			String email = user.getEmail();
			completedApproval = true;
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return completedApproval;
	}

	/**
	 * This method updates FamilyInventory with the "approved" status.
	 *
	 * @param familyInventory
	 * @return
	 */
	private boolean updateFamilyInventory(FamilyInventory familyInventory) {
		boolean isUpdated = false;
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(FamilyInventory.class);
			criteria.add(Restrictions.eq("id", familyInventory.getId()));
			FamilyInventory updateFamilyInventory = (FamilyInventory) criteria.list().get(0);
			updateFamilyInventory.setStatus(familyInventory.getStatus());
			session.update(updateFamilyInventory);
			isUpdated = true;
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	/**
	 * This method allows homevisitor to submit the requests.
	 *
	 * @param userId
	 * @param familyId
	 * @param inventoryId
	 * @param quantity
	 * @return
	 */
	public boolean submitRequests(int userId, String familyId, int inventoryId, int quantity) {
		boolean isSubmited = false;
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();

			// Add new FamilyInventory
			FamilyInventory familyInventory = new FamilyInventory();
			familyInventory.setFamilyId(familyId);
			familyInventory.setQuantity(quantity);
			familyInventory.setStatus("pending");
			familyInventory.setInventoryId(inventoryId);
			familyInventory.setRequestedDate(new Timestamp(Instant.now().getEpochSecond()));
			saveFamilyInventory(familyInventory);

			tx.commit();

			// // send notification to the supervisor
			// Criteria criteria = session.createCriteria(User.class);
			// criteria.add(Restrictions.eq("id", userId));
			// User user = (User) criteria.list().get(0);
			// String supervisorIdStr = user.getSupervisoremail();
			// sendNotification(supervisorId, familyInventory);

			isSubmited = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSubmited;
	}

	/**
	 * This method adds a new FamilyInventory.
	 *
	 * @param familyInventory
	 * @return
	 */
	public boolean saveFamilyInventory(FamilyInventory familyInventory) {
		boolean isSaved = false;
		Transaction tx = null;
		try {
			Session session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(familyInventory);
			tx.commit();
			isSaved = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSaved;
	}
}
