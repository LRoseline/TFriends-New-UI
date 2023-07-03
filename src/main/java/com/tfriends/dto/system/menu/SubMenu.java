package com.tfriends.dto.system.menu;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SubMenu {
    private int listorder;
    private String name;
    private String type;
    private String url;
}
