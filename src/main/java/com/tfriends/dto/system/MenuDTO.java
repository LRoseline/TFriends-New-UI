package com.tfriends.dto.system;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDTO {
    private int menuno;
    private String menuname;
    private String type;
    private String menuurl;
    private String parentby;
    private String listorder;
    private List<MenuDTO> child;
}
