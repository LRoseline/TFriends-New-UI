package com.tfriends.dto.pagination;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationDTOV2 extends SearchDTOV2 {
    private int start, end;

    public void setTotalpage(int articleCount) {
        this.start = 1;
        this.end = (int) Math.ceil(articleCount / (double) getLimit());
    }
}
