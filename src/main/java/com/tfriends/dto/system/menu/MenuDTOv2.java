package com.tfriends.dto.system.menu;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDTOv2 {
    private int no;
    private String name;
    private Type type;
    private String url;
    private List<SubMenu> submenu;
}
