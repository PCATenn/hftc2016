package org.pcat.inventory.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.pcat.inventory.TestHelper;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.RequestItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;

//@RunWith(SpringRunner.class)
// @ContextConfiguration(locations = { "classpath:test-serviceContext.xml" })
public class RequestFamilyItemsServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(RequestFamilyItemsServiceTest.class);
	private static TestHelper helper = new TestHelper();

	@BeforeClass
	public static void setup() {
//		helper.saveCurrentRootLogging();
//		helper.setRootTestLoggerLevel(Level.INFO);
	}

	@AfterClass
	public static void tearDown() {
//		helper.resetSaveRootLoggerLevel();
	}

	private RequestFamilyItemsService requestFamilyItemsService = new RequestFamilyItemsService();

	@Test
	public void didMailRequestHappen() {
		final String familyNumber = "TEST-0001";
		
		final MailService ms = mock(MailService.class);
		requestFamilyItemsService.setMailService(ms);
		
		final InventoryBO invBizObj = mock(InventoryBO.class);
		requestFamilyItemsService.setInventoryBusinessObject(invBizObj);
		
		final HomeVisitorEmailRequestBO emailUtility = mock(HomeVisitorEmailRequestBO.class);
		requestFamilyItemsService.setRequestUtility(emailUtility);

		ArrayList<RequestItem> requestItems = new ArrayList<RequestItem>();
		for (int x = 1; x < 7; x++) {
			requestItems.add(new RequestItem(x, 1));
		}

		List<String> items = new ArrayList<String>();
		for (int x = 1; x < 7; x++) {
			items.add(String.format("Item %d", x));
		}
		when(invBizObj.getItemDescriptions(requestItems)).thenReturn(items);

		final HomeVisitor homeVisitor = new HomeVisitor("testFirstName", "testLastName", "testEmail",
				"testSupervisorEmail");

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
		verify(ms).sendMail(homeVisitor.getEmail(), homeVisitor.getSupervisorEmail(), testSubject, testMessage.toString());
	}

}
