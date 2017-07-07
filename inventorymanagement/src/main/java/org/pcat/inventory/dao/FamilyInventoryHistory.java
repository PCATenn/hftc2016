package org.pcat.inventory.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class FamilyInventoryHistory extends BaseDao {

	@Override
	public FamilyInventoryHistory getById(int id) {
		final FamilyInventoryHistory inventory = (FamilyInventoryHistory) super.getById(
				FamilyInventoryHistory.class, id);
		return inventory;
	}

	public List<FamilyInventoryHistory> findAll() {
		List<FamilyInventoryHistory> FamilyInventoryHistorys = (List<FamilyInventoryHistory>) super.findAll(FamilyInventoryHistory.class);
		return FamilyInventoryHistorys;
	}

	public List<FamilyInventoryHistory> findAllForIds(List<Integer> homeVisitorIds) {
		List<FamilyInventoryHistory> result = null;
		Session session = null;
		try {
			Criteria criteria = getSession().createCriteria(FamilyInventoryHistory.class);
			criteria.add(Restrictions.in("requestorId", homeVisitorIds));
			result = (List<FamilyInventoryHistory>) criteria.list();
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
