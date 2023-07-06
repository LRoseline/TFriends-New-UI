package com.tfriends.dto.cms;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WidgetDTOv2 {
    private String dbname;
    private String name;
    private String hash;
    private List<DefaultDTOv2> articles;
}
