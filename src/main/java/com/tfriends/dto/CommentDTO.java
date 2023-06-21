package com.tfriends.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private int rno;
    private int uno;
    private String name;
    private String content;
    private List<CommentDTO> reply;
}
