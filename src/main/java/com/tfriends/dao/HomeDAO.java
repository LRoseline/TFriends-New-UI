package com.tfriends.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tfriends.dto.system.MenuDTO;

@Mapper
public interface HomeDAO {
    public List<MenuDTO> menuLoad ();
    
    @Select("SELECT `content` FROM account_union.options WHERE `type` = 'loginURL'")
    public String subURL();
}
