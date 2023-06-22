package com.tfriends.dto.pagination;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTOV2 {
    private String search_type, word;

    private int page, limit;

    public SearchDTOV2() {
        this.search_type = "all";
        this.word = "";

        this.page = 1;
        this.limit = 10;
    }

    @JsonIgnore
    public int getFirstArticle() {
        return (this.page - 1) * limit;
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
