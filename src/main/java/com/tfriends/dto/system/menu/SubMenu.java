package com.tfriends.dto.system.menu;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SubMenu {
    private int listorder;
    private String name;
    private Type type;
    private String url;
}
