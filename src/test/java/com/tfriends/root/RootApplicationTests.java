package com.tfriends.root;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tfriends.dao.cms.ShortBoard;
import com.tfriends.dto.cms.WidgetDTOv2;
import com.tfriends.service.AccountService;

@SpringBootTest
class RootApplicationTests {

	@Autowired
	private AccountService service;

	@Test
	void contextLoads() {
		java.util.Date date = new java.util.Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 3);

		int compare = cal.getTime().compareTo(service.getLogin(1).getPasswordExpired());

		System.out.println(compare);
	}

	@Autowired
	private ShortBoard shorts;

	@Test
	public void testBoard() {
		List<WidgetDTOv2> argument = shorts.boardList();
		System.out.println(argument);

		for (int i = 0; i < argument.size(); i++) {
			System.out.println(i+" Board");
			System.out.println(argument.get(i));
		}

		System.out.println(shorts.articleResult(argument));
	}
}
