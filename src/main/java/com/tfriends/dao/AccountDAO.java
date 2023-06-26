package com.tfriends.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tfriends.dto.AccountDTO;

@Mapper
public interface AccountDAO {
    public AccountDTO loginInfo(@Param("uno") long uno);
}
