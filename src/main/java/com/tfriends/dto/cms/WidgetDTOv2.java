package com.tfriends.dto.cms;

import org.springframework.beans.factory.annotation.Autowired;

public class WidgetDTOv2 {

    @Autowired

    public SecureDTO miniBoard(String hash) {
        return new SecureDTO();
    }
}
