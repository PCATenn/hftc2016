package org.pcat.inventory.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.pcat.inventory.model.HomeVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeVisitorEmailRequestBOTest {
	private static final Logger logger = LoggerFactory.getLogger(HomeVisitorEmailRequestBOTest.class);
	HomeVisitorEmailRequestBO emailUtility = new HomeVisitorEmailRequestBO();

	@Test
	public void messageBodyBuilderTest() {
		String newline = System.getProperty("line.separator");
		HomeVisitor homeVisitor = new HomeVisitor("testFirst", "testLast", "testEmail@mailinator.com",
				"testSupervisorEmail@mailinator.com");
		String testBody = "These items have been requested by testFirst testLast: " + newline + "Item 1" + newline
				+ "Item 2" + newline + "Item 3" + newline + "Item 4" + newline + "Item 5" + newline + "Item 6";
		List<String> items = new ArrayList<String>();
		items.add("Item 1");
		items.add("Item 2");
		items.add("Item 3");
		items.add("Item 4");
		items.add("Item 5");
		items.add("Item 6");
		String renderedString = emailUtility.getMessageBody(homeVisitor.getFirstname(), homeVisitor.getLastname(),
				items);
		logger.info(renderedString);
		assertThat(renderedString, equalTo(testBody));
	}
}
