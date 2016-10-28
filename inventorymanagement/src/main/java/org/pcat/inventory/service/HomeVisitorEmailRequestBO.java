package org.pcat.inventory.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HomeVisitorEmailRequestBO {
	public static final String HOME_VISITOR_SUBJECT = "Requesting supplies for family %s";
	public static final String HOME_VISITOR_MESSAGE = "These items have been requested by %s %s: %s";

	public String getMessageBody(String firstname, String lastname, List<String> itemList) {
		String newline = System.getProperty("line.separator");
		StringBuffer items = new StringBuffer();
		itemList.forEach(item -> items.append(newline + item));

		return String.format(HOME_VISITOR_MESSAGE, firstname, lastname, items);
	}

}
