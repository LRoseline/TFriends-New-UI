package com.tfriends.dto.index;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HomeDTO {
    private int no;
    private String name;
    private String description;
    private Object value;
    private Option tag;
}
