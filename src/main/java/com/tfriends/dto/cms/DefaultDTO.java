package com.tfriends.dto.cms;

import java.sql.Date;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date regdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update;

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
        java.util.Date date = new java.util.Date(regdate.getTime());
        java.util.Date now = new java.util.Date();

        calNowDate.setTime(now);

        calOldDate.setTime(date);
        calOldDate.add(Calendar.WEEK_OF_MONTH, 1);

        int isOlder = calOldDate.compareTo(calNowDate);

        return isOlder;
    }
}
