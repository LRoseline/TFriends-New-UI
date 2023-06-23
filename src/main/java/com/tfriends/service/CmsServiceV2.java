package com.tfriends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfriends.dao.CmsDAOV2;
import com.tfriends.dto.cms.DefaultDTO;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.dto.system.ErrorDTO;
import com.tfriends.dto.system.TrashDTO;

@Service
public class CmsServiceV2 {

    @Autowired
    private CmsDAOV2 dao;

    SecureDTO secure;
    int code = 100;

    public SecureDTO userBoardList(String hash, PaginationDTOV2 page) {
        secure = dao.secureWindow(hash);

        if (secure != null) {
            secure.setPage(page);
            page.setTotalpage(dao.boardCount(secure.getBoard(), page));

            if (page.getEnd() < page.getPage()) {
                page.setPage(page.getEnd());
            }

            if (page.getPage() <= 0) {
                page.setPage(1);
            }

            secure.setResult(dao.boardList(secure.getBoard(), page));
            code = 200;
        } else {
            secure = new SecureDTO();
            code = 404;
        }
        secure.setStatus(new ErrorDTO(code));
        return secure;
    }

    public SecureDTO userBoardRead(String hash, int no, PaginationDTOV2 page) {
        secure = dao.secureWindow(hash);

        if (secure != null) {
            DefaultDTO article = dao.boardArticle(secure.getBoard(), no);
            if (secure.getPermission().getRead() == 0 && article != null) {
                secure.setPage(page);
                secure.setResult(article);
                code = 200;
            } else {
                code = 403;
                secure.setResult("권한이 없거나 해당 게시글이 없습니다.");
            }
        } else {
            secure = new SecureDTO();
            code = 404;
        }

        secure.setStatus(new ErrorDTO(code));
        return secure;
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
