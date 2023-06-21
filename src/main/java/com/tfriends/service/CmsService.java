package com.tfriends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfriends.dao.CmsDAO;
import com.tfriends.dto.cms.DefaultDTO;
import com.tfriends.dto.pagination.CountPageDTO;
import com.tfriends.dto.system.PermissionDTO;
import com.tfriends.dto.system.TrashDTO;

@Service
public class CmsService {

    @Autowired
    private CmsDAO dao;

    public List<DefaultDTO> shortCommunityBoard(String board) {
        return dao.widgetBoard(board);
    }

    public List<DefaultDTO> userBoardList(CountPageDTO dto) {
        return dao.boardList(dto);
    }

    public int userBoardCount(CountPageDTO dto) {
        return dao.boardCount(dto);
    }

    public DefaultDTO userBoardRead(String board, int no) {
        return dao.boardArticle(board, no);
    }

    public void regArticle(String board, DefaultDTO dto) {
        dao.newArticle(board, dto);
    }

    public void editArticle(String board, DefaultDTO dto) {
        dao.updateArticle(board, dto);
    }

    public boolean deleteArticle(String category, int i) {
        System.out.println("게시판 : " + category + ", 번호 : " + i);

        return dao.delArticle(category, i) == 1;
    }

    public void moveTrashcan(TrashDTO dto) {
        dao.newTrash(dto);
    }

    public List<TrashDTO> bearerTrash() {
        return dao.listRecycle();
    }

    public PermissionDTO getBoardPermission(String type, String menuurl) {
        return dao.menuPermission(type, menuurl);
    }
}
