package com.tfriends.dto.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountPageDTOV2 {
    private int page, limit;

    public CountPageDTOV2() {
        this.page = 1;
        this.limit = 10;
    }

    public int getFirstArticle() {
        return (this.page - 1) * limit;
    }
}
