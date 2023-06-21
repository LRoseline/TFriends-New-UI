package com.tfriends.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.tfriends.dto.system.MenuDTO;

@Mapper
public interface HomeDAO {
    public List<MenuDTO> menuLoad ();
}
