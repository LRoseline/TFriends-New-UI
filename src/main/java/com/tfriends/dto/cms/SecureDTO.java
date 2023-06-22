package com.tfriends.dto.cms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.dto.system.ErrorDTO;
import com.tfriends.dto.system.PermissionDTOv2;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecureDTO {
    private ErrorDTO status;

    @JsonIgnore
    private int no;

    private String type, name;

    @JsonIgnore
    private String url;

    @JsonIgnore
    public String getBoard() {
        return this.getType() + "_" + this.getUrl();
    }

    private PermissionDTOv2 permission;
    private PaginationDTOV2 page;
    private Object result;
}
