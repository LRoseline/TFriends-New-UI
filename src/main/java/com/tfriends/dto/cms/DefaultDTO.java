package com.tfriends.dto.cms;

import java.util.Date;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.tfriends.dto.BBCodeFilter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultDTO {
    private int no;
    private String title;

    private long writer;
    private String name;
    private String gravatar;

    private String roles;

    private java.sql.Date regdate;
    private java.sql.Date update;

    // bb코드 원본
    private String content;

    /* htmlContent는 bb코드를 html코드로 변환시켜주는 객체예요. */
    @JsonIgnore
    public String getHtmlContent() {
        BBCodeFilter bb = new BBCodeFilter();
        String htmlContent = bb.bbFilter(content);

        return htmlContent;
    }

    public int getNewArticle() {
        Calendar calNowDate = Calendar.getInstance();
        Calendar calOldDate = Calendar.getInstance();
        Date date = new Date(regdate.getTime());
        Date now = new Date();

        calNowDate.setTime(now);

        calOldDate.setTime(date);
        calOldDate.add(Calendar.WEEK_OF_MONTH, 1);

        int isOlder = calOldDate.compareTo(calNowDate);

        return isOlder;
    }
}
