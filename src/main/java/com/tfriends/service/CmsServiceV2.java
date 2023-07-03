package com.tfriends.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfriends.dao.CmsDAOV2;
import com.tfriends.dao.SystemDAO;
import com.tfriends.dto.AccountDTO;
import com.tfriends.dto.cms.DefaultDTOv2;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.dto.system.ErrorDTO;
import com.tfriends.dto.system.TrashDTO;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CmsServiceV2 {

    @Autowired
    private CmsDAOV2 dao;

    @Autowired
    private AccountService accounts;

    @Autowired
    private SystemDAO sysdao;

    @Autowired
    private HttpServletRequest req;

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
            DefaultDTOv2 article = dao.boardArticle(secure.getBoard(), no);
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

    public SecureDTO regArticle(String hash, DefaultDTOv2 dto) {
        try {
            AccountDTO user = accounts.getAuthen();
            secure = dao.secureWindow(hash);

            if (secure != null && dto.getWriter() == 0) {
                if (secure.getPermission().getWrite() <= user.getGrade()) {
                    dto.setWriter(user.getUno());
                    dao.newArticle(secure.getBoard(), dto);
                    secure.setResult("게시글이 정상적으로 등록되었습니다.");
                    code = 201;
                } else {
                    code = 403;
                    secure.setResult("게시판 쓰기 권한이 없습니다.");
                }
            } else {
                secure = new SecureDTO();
                code = 405;
            }
        } catch (Exception e) {
            secure = new SecureDTO();
            code = 401;
        }

        secure.setStatus(new ErrorDTO(code));
        return secure;
    }

    public SecureDTO editArticle(String hash, DefaultDTOv2 dto, int no) {
        try {
            AccountDTO user = accounts.getAuthen();
            secure = dao.secureWindow(hash);
            if (secure != null && dto.getWriter() == 0 && dto.getNo() == 0) {
                dto.setNo(no);
                dto.setWriter(user.getUno());

                dao.updateArticle(secure.getBoard(), dto);
                secure.setResult(dao.boardArticle(secure.getBoard(), no));
                code = 200;
            } else {
                secure = new SecureDTO();
                code = 405;
            }
        } catch (Exception e) {
            secure = new SecureDTO();
            code = 401;
        }

        secure.setStatus(new ErrorDTO(code));
        return secure;
    }

    public SecureDTO deleteArticle(String hash, int no) {
        try {
            AccountDTO user = accounts.getAuthen();
            secure = dao.secureWindow(hash);
            
            if (secure != null) {
                DefaultDTOv2 article = dao.boardArticle(secure.getBoard(), no);
                deleteArticleThrow(user, secure.getBoard(), no, article);
                code = 200;
            } else {
                secure = new SecureDTO();
                code = 405;
            }
        } catch (Exception e) {
            secure = new SecureDTO();
            code = 401;
        }

        secure.setStatus(new ErrorDTO(code));
        return secure;
    }

    private void deleteArticleThrow(AccountDTO a, String category, int rno, DefaultDTOv2 article) throws Exception {
        ObjectMapper obj = new ObjectMapper();
        String body = obj.writeValueAsString(article);

        String ipRequest = req.getHeader("CF-Connecting-IP");
        if (ipRequest == null) {
            ipRequest = req.getRemoteAddr();
        }

        TrashDTO trash = new TrashDTO(a.getUno(), a.getMail(), ipRequest, body);

        sysdao.newTrash(trash);
        dao.delArticle(category, rno);
    }
}
