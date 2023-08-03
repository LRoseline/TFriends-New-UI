package com.tfriends.dao.cms;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tfriends.dto.cms.WidgetDTOv2;

@Mapper
public interface ShortBoard {

    @Select("SELECT CONCAT(`type`,\"_\",`url`) AS `dbname`, `name`, `hash` FROM boards WHERE `shortView` = true")
    public List<WidgetDTOv2> boardList();

    public List<WidgetDTOv2> articleResult(@Param("boardlists") List<WidgetDTOv2> boardlists);
}
