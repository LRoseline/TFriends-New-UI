package com.tfriends.dto.system.bw;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BwIpList {
    private String ip;
    private String reason;
    private BwType type;
    private BwTime dt;
}
