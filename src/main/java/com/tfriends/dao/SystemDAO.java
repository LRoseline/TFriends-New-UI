package com.tfriends.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tfriends.dto.AccountDTO;
import com.tfriends.dto.system.TrashDTO;
import com.tfriends.dto.system.menu.MenuDTOv2;

@Mapper
public interface SystemDAO {
    public List<MenuDTOv2> menuLoad();

    @Select("SELECT `content` FROM account_union.options WHERE `type` = 'loginURL2'")
    public String subURL();

    public AccountDTO loginInfo(@Param("uno") long uno);

    @Insert("INSERT INTO manager.trashcanboard (`body`, `workedbyid`, `workedbymail`, `workedbyip`) VALUES (#{body}, #{workedbyid}, #{workedbymail}, #{workedbyip})")
    public void newTrash(TrashDTO dto);

    @Select("SELECT * FROM manager.trashcanboard")
    public List<TrashDTO> listRecycle();
}
