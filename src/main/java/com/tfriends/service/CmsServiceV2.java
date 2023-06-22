package com.tfriends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfriends.dao.CmsDAOV2;
import com.tfriends.dto.cms.DefaultDTO;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.dto.system.TrashDTO;

@Service
public class CmsServiceV2 {

    @Autowired
    private CmsDAOV2 dao;

    public SecureDTO userBoardList(String hash, PaginationDTOV2 page) {
        SecureDTO secure = dao.secureWindow(hash);
        page.setTotalpage(dao.boardCount(secure.getBoard(), page));

        if (page.getEnd() < page.getPage()) {
            page.setPage(page.getEnd());
        }

        if (page.getPage() <= 0) {
            page.setPage(1);
        }

        secure.setResult(dao.boardList(secure.getBoard(), page));

        return secure;
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
}
