package com.tfriends.dto.system.bw;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BwTime {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date start;

    private String end;
}
