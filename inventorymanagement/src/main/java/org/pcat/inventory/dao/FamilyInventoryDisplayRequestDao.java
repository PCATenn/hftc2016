package org.pcat.inventory.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.FamilyInventoryDisplayRequest;
import org.pcat.inventory.model.Inventory;
import org.springframework.stereotype.Repository;

@Repository
public class FamilyInventoryDisplayRequestDao extends BaseDao {

	@Override
	public FamilyInventoryDisplayRequest getById(int id) {
		final FamilyInventoryDisplayRequest inventory = (FamilyInventoryDisplayRequest) super.getById(
				FamilyInventoryDisplayRequest.class, id);
		return inventory;
	}

	public List<FamilyInventoryDisplayRequest> findAll() {
		List<FamilyInventoryDisplayRequest> familyInventoryDisplayRequests = (List<FamilyInventoryDisplayRequest>) super.findAll(FamilyInventoryDisplayRequest.class);
		return familyInventoryDisplayRequests;
	}
	public List<FamilyInventoryDisplayRequest> findAllPending() {
		List<FamilyInventoryDisplayRequest> result = null;
		Session session = null;
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(FamilyInventoryDisplayRequest.class);
			criteria.add(Restrictions.eq("status", "Pending"));
			result = criteria.list();
		} catch (Exception e) {
			handleException(e, null);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return result;
	}

	public List<FamilyInventoryDisplayRequest> findAllForIds(List<Integer> homeVisitorIds) {
		List<FamilyInventoryDisplayRequest> result = null;
		Session session = null;
		try {
			Criteria criteria = getSession().createCriteria(FamilyInventoryDisplayRequest.class);
			criteria.add(Restrictions.in("requestorId", homeVisitorIds));
			result = (List<FamilyInventoryDisplayRequest>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}
}
