package com.tfriends.dto.cms;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Timestamp {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update;
}
