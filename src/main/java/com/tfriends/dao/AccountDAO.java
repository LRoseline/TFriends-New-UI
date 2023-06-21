package com.tfriends.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tfriends.dto.AccountDTO;

@Mapper
public interface AccountDAO {
    public AccountDTO loginInfo(@Param("uno") long uno);

    @Select("SELECT `content` FROM account_union.options WHERE `type` = 'loginURL'")
    public String subURL();
}
