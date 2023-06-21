package com.tfriends.dto.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountPageDTO {
    private int page, limit;
    private String board;

    public CountPageDTO() {
        this.page = 1;
        this.limit = 10;
    }

    public int getFirstArticle() {
        return (this.page - 1) * limit;
    }
}
