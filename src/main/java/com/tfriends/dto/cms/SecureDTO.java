package com.tfriends.dto.cms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tfriends.dto.system.PermissionDTOv2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SecureDTO {
    @JsonIgnore
    private int no;
    private String name;

    @JsonIgnore
    private String type, url;

    @JsonIgnore
    public String getBoard() {
        return this.getType() + "_" + this.getUrl();
    }

    public PermissionDTOv2 permission;

    public Object result;
}
