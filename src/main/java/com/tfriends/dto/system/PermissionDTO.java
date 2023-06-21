package com.tfriends.dto.system;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PermissionDTO {
    private String menuurl;
    private String menuname;
    private String subname;
    private long permissionWrite;
}
