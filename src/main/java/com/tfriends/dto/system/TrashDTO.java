package com.tfriends.dto.system;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TrashDTO {
    private Object body;
    private long workedbyid;
    private String workedbymail;
    private String workedbyip;

    public TrashDTO(long workedbyid, String workedbymail, String workedbyip, Object body) {
        this.workedbyid = workedbyid;
        this.body = body;
        this.workedbymail = workedbymail;
        this.workedbyip = workedbyip;
    }
}
