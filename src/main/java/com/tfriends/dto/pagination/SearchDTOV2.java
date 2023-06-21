package com.tfriends.dto.pagination;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTOV2 extends CountPageDTOV2 {
    private String search_type;
    private String word;

    public SearchDTOV2() {
        this.search_type = "all";
        this.word = "";
    }

    public String uriQuerys(int page) {
        UriComponents uri = UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("search_type", search_type)
                .queryParam("word", word)
                .build().encode();

        return uri.toUriString();
    }
}
