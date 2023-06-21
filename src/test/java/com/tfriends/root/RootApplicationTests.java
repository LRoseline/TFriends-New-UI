package com.tfriends.root;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
