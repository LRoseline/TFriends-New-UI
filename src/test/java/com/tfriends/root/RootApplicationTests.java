package com.tfriends.root;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tfriends.dao.cms.ShortBoard;
import com.tfriends.dto.cms.WidgetDTOv2;
import com.tfriends.service.SystemService;

@SpringBootTest
class RootApplicationTests {

	@Autowired
	private ShortBoard shorts;

	@Test
	public void testBoard() {
		List<WidgetDTOv2> argument = shorts.boardList();
		System.out.println(argument);

		for (int i = 0; i < argument.size(); i++) {
			System.out.println(i + " Board");
			System.out.println(argument.get(i));
		}

		System.out.println(shorts.articleResult(argument));
	}

	@Autowired
	private SystemService service;

	@Test
	public void testIndex() {
		System.out.println(service.optionDetail("homescreen"));
	}

}
