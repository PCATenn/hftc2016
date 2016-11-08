package org.pcat.inventory.dao;

import org.pcat.inventory.model.FamilyInventory;
import org.springframework.stereotype.Repository;

@Repository
public class FamilyInventoryDao extends BaseDao {

	@Override
	public FamilyInventory getById(int id) {
		final FamilyInventory inventory = (FamilyInventory) super.getById(FamilyInventory.class, id);
		return inventory;
	}

}
