package org.pcat.inventory.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pcat.inventory.TestHelper;
import org.pcat.inventory.dao.FamilyInventoryDao;
import org.pcat.inventory.dao.InventoryDao;
import org.pcat.inventory.model.FamilyInventory;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.Inventory;
import org.pcat.inventory.model.RequestItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

public class RequestFamilyItemsServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(RequestFamilyItemsServiceTest.class);
	private static TestHelper helper = new TestHelper();

	@BeforeClass
	public static void setup() {
		helper.saveCurrentRootLogging();
		helper.setRootTestLoggerLevel(Level.DEBUG);
	}

	@AfterClass
	public static void tearDown() {
		helper.resetSaveRootLoggerLevel();
	}

	private RequestFamilyItemsService requestFamilyItemsService = new RequestFamilyItemsService();

	@Test
	public void requestItemsTest() {
		final String familyNumber = "TEST-0001";
		final HomeVisitor homeVisitor = new HomeVisitor("testFirstName", "testLastName", "testEmail",
				"testSupervisorEmail");

		InventoryDao mockInventoryDao = mock(InventoryDao.class);
		requestFamilyItemsService.setInventoryDao(mockInventoryDao);

		FamilyInventoryDao mockFamilyInventoryDao = mock(FamilyInventoryDao.class);
		requestFamilyItemsService.setFamilyInventoryDao(mockFamilyInventoryDao);

		final MailService ms = mock(MailService.class);
		requestFamilyItemsService.setMailService(ms);

		final InventoryBO invBizObj = mock(InventoryBO.class);
		requestFamilyItemsService.setInventoryBusinessObject(invBizObj);

		final HomeVisitorEmailRequestBO emailUtility = mock(HomeVisitorEmailRequestBO.class);
		requestFamilyItemsService.setRequestUtility(emailUtility);

		ArrayList<RequestItem> requestItems = new ArrayList<RequestItem>();
		for (int x = 1; x < 7; x++) {
			requestItems.add(new RequestItem(x, 1, null));
		}
		/* Inventory retrieval and family inventory data verification setup */
		List<Inventory> mockedInventoryList = new ArrayList<Inventory>();
		List<FamilyInventory> mockedFamilyInventories = new ArrayList<>();
		final Timestamp ts = new Timestamp(Instant.now().getEpochSecond());
		for (int x = 1; x < 7; x++) {
			String nameAndDesc = String.format("Item %d", x);
			mockedInventoryList.add(new Inventory(x, nameAndDesc, nameAndDesc, 12, 3, "Nashville"));
			mockedFamilyInventories.add(new FamilyInventory(null, familyNumber, "Pending", 1, ts, x));
		}
		for (int x = 0; x < 6; x++) {
			when(mockInventoryDao.getById(x + 1)).thenReturn(mockedInventoryList.get(x));
			when(mockFamilyInventoryDao.getById(x + 1)).thenReturn(mockedFamilyInventories.get(x));
		}
		/* Family inventory request verification object */
		/* Email list setup */
		List<String> items = new ArrayList<String>();
		for (int x = 1; x < 7; x++) {
			items.add(String.format("Item %d", x));
		}
		when(invBizObj.getItemDescriptions(requestItems)).thenReturn(items);

		final String testSubject = "Requesting supplies for family TEST-0001";
		logger.debug(String.format("subject: %s", testSubject));

		final String newline = System.getProperty("line.separator");
		StringBuffer testMessage = new StringBuffer("These items have been requested by testFirstName testLastName: ");
		for (int x = 1; x < 7; x++) {
			testMessage.append(String.format("%sItem %d", newline, x));
		}
		when(emailUtility.getMessageBody("testFirstName", "testLastName", items)).thenReturn(testMessage.toString());
		logger.debug(String.format("body:  %s", testMessage));

		requestFamilyItemsService.requestItems(familyNumber, requestItems, homeVisitor);
		/* Test that the inventory items were requested properly */
		/* Test that the request to create the family inventory was correctly */
		for (int x = 1; x < 7; x++) {
			verify(mockInventoryDao).getById(x);
		}
		/* Test that the family inventory requests are created correctly */
		verify(mockFamilyInventoryDao, times(6)).saveOrUpdate(mockedFamilyInventories.get(0));
		/* Test the requestItems have the right inventory attached */
		requestItems.forEach(item -> assertThat(verifyInventoryItem(item, mockInventoryDao), equalTo(true)));
		/* Test that the email was requested properly */
		verify(ms).sendMail(homeVisitor.getEmail(), homeVisitor.getSupervisorEmail(), homeVisitor.getEmail(),
				testSubject, testMessage.toString());

		/* Test that the inventory are updated correctly */
	}

	private boolean verifyInventoryItem(RequestItem item, InventoryDao mockInventoryDao) {
		final Inventory inventory = item.getRequestInventory();
		assertThat(item.getId(), equalTo(inventory.getId()));
		assertThat(inventory.getReservedInventory(), equalTo(4));
		assertThat(inventory.getTotalInventory(), equalTo(12));
		verify(mockInventoryDao).saveOrUpdate(inventory);
		return true;
	}
}
