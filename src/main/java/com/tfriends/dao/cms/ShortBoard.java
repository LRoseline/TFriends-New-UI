package com.tfriends.dao.cms;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tfriends.dto.cms.WidgetDTOv2;

@Mapper
public interface ShortBoard {

    @Select("SELECT CONCAT(`type`,\"_\",`url`) AS `board` FROM boards")
    public List<String> boardList();

    public List<WidgetDTOv2> articleResult();
}
