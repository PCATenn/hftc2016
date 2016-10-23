package org.pcat.inventory.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pcat.inventory.model.HomeVisitor;
import org.pcat.inventory.model.RequestItem;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// @ContextConfiguration(locations = { "classpath:test-serviceContext.xml" })
public class RequestFamilyItemsServiceTest {

	private RequestFamilyItemsService requestFamilyItemsService = new RequestFamilyItemsService();

	@Test
	public void didMailRequestHappen() {
		final String familyNumber = "TEST-0001";
		final MailService ms = mock(MailService.class);
		requestFamilyItemsService.setMailService(ms);

		ArrayList<RequestItem> requestItems = new ArrayList<RequestItem>();
		requestItems.add(new RequestItem(1, 1));
		requestItems.add(new RequestItem(2, 1));
		requestItems.add(new RequestItem(3, 1));
		requestItems.add(new RequestItem(4, 1));
		requestItems.add(new RequestItem(5, 1));
		requestItems.add(new RequestItem(6, 1));

		List<String> items = new ArrayList<String>();
		items.add("Item 1");
		items.add("Item 2");
		items.add("Item 3");
		items.add("Item 4");
		items.add("Item 5");
		items.add("Item 6");

		final HomeVisitor homeVisitor = new HomeVisitor("firstName", "lastName", "email", "supervisorEmail");
		final String subject = String.format(HomeVisitorEmailRequestUtility.HOME_VISITOR_SUBJECT, familyNumber);
		final String message = new HomeVisitorEmailRequestUtility().getMessageBody(homeVisitor.getFirstname(),
				homeVisitor.getLastname(), items);

		requestFamilyItemsService.requestItems(familyNumber, requestItems, homeVisitor);
		verify(ms).sendMail(homeVisitor.getFirstname(), homeVisitor.getLastname(), subject, message);
	}

}
/*
 * package org.pcat.inventory.service;
 * 
 * import static org.hamcrest.CoreMatchers.equalTo; import static
 * org.hamcrest.MatcherAssert.assertThat;
 * 
 * import org.junit.Test; import org.junit.runner.RunWith; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.test.context.ContextConfiguration; import
 * org.springframework.test.context.junit4.SpringRunner;
 * 
 * 
 * public class MailSendTest {
 * 
 * @Autowired private MailService mailService;
 * 
 * @Test public void sendHomeUserEmailIntegrationTest() {
 * 
 * String message = "Requesting supplies for family DAVI-0001"; String subject =
 * "Subj:  Supplies for family DAVI-0001";
 * mailService.sendMail("testFrom.pcat@mailinator.com",
 * "testTo.pcat@mailinator.com", subject, message); }
 * 
 * }
 */