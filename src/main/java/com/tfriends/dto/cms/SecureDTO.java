package com.tfriends.dto.cms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SecureDTO {
    private int no;
    private String type, url, name, hash;
}
