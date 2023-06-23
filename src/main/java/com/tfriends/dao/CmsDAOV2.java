package com.tfriends.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tfriends.dto.cms.DefaultDTOv2;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.dto.system.TrashDTO;

@Mapper
public interface CmsDAOV2 {
    public SecureDTO secureWindow(String hash);

    public List<DefaultDTOv2> boardList(@Param("board") String board, @Param("dto") PaginationDTOV2 page);

    public int boardCount(@Param("board") String board, @Param("dto") PaginationDTOV2 page);

    public DefaultDTOv2 boardArticle(@Param("board") String board, @Param("no") int no);

    public void newArticle(@Param("board") String board, @Param("dto") DefaultDTOv2 dto);

    public void updateArticle(@Param("board") String board, @Param("dto") DefaultDTOv2 dto);

    @Delete("DELETE FROM `community_${board}` WHERE `no` = #{no}")
    public int delArticle(@Param("board") String board, @Param("no") int i);

    public void newTrash(TrashDTO dto);

    public List<TrashDTO> listRecycle();
}
