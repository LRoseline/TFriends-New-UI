package com.tfriends.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.tfriends.dto.cms.DefaultDTO;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.CountPageDTOV2;
import com.tfriends.dto.system.PermissionDTO;
import com.tfriends.dto.system.TrashDTO;

@Mapper
public interface CmsDAOV2 {
    @Select("SELECT `type`,`url`,`name`,`write`,`read` FROM boards WHERE `hash` = #{hash}")
    public SecureDTO secureWindow(String hash);

    public List<DefaultDTO> boardList(@Param("board") String board, @Param("dto") CountPageDTOV2 dto);

    public int boardCount(@Param("board") String board, @Param("dto") CountPageDTOV2 dto);

    public DefaultDTO boardArticle(@Param("board") String board, @Param("no") int no);

    public void newArticle(@Param("board") String board, @Param("dto") DefaultDTO dto);

    public void updateArticle(@Param("board") String board, @Param("dto") DefaultDTO dto);

    @Delete("DELETE FROM `community_${board}` WHERE `no` = #{no}")
    public int delArticle(@Param("board") String board, @Param("no") int i);

    public void newTrash(TrashDTO dto);

    public List<TrashDTO> listRecycle();

    @Select("SELECT `menuname`, `subname`, `menuurl`, `permissionWrite` FROM `menu` WHERE type = #{type} AND menuurl = #{url}")
    public PermissionDTO menuPermission(@Param("type") String type, @Param("url") String menuurl);

    @Select("SELECT `no`, `title`, `regdate` FROM `community_${boardname}` ORDER BY `no` DESC limit 0,5")
    public List<DefaultDTO> widgetBoard(@Param("boardname") String board);
}
