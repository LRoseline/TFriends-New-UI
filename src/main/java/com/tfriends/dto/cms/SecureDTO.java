package com.tfriends.dto.cms;

import java.util.List;

import com.tfriends.dto.system.PermissionDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SecureDTO {
    private int no;
    private String type, url, name, hash;

    private List<PermissionDTO> permission;

    public String getBoard() {
        return this.getType() + "_" + this.getUrl();
    }
}
