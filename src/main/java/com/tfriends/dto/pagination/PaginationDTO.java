package com.tfriends.dto.pagination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationDTO {
    private int start, end;
    private int totalpage;

    private int pageJumpNum;

    private CountPageDTO count;

    private void calc() {
        this.pageJumpNum = 10;

        this.start = 1;
        this.end = (int)Math.ceil(totalpage / (double)count.getLimit());
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
        
        calc();
    }
}
