package com.tfriends.dto.cms;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnADTO extends DefaultDTOv2 {
    private List<DefaultDTOv2> reply;
}
