package com.tfriends.root;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tfriends.dto.BBCodeFilter;
import com.tfriends.dto.cms.DefaultDTO;
import com.tfriends.service.CmsServiceV2;

@SpringBootTest
public class BBCodeTest {

    @Autowired
    private CmsServiceV2 s;

    final String bar = "============================================";

    @Test
    public void contextLoads() {
        BBCodeFilter filter = new BBCodeFilter();
        DefaultDTO cont = s.userBoardRead("test", 2);
        System.out.println(bar);
        System.out.println(cont.getTitle());
        System.out.println(bar);
        System.out.println(filter.bbFilter(cont.getContent()));
    }
}
