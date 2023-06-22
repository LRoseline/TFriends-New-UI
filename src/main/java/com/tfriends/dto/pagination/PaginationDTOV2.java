package com.tfriends.dto.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDTOV2 extends SearchDTOV2 {
    private int start, end;
    private int totalpage;

    private int pageJumpNum;

    // private SearchDTOV2 count;

    private void calc() {
        this.pageJumpNum = 10;

        this.start = 1;
        this.end = (int) Math.ceil(totalpage / (double) getLimit());
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;

        calc();
    }
}
