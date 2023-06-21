package com.tfriends.root.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tfriends.dto.AccountDTO;
import com.tfriends.dto.cms.DefaultDTO;
import com.tfriends.dto.pagination.PaginationDTO;
import com.tfriends.dto.pagination.SearchDTO;
import com.tfriends.dto.system.PermissionDTO;
import com.tfriends.dto.system.TrashDTO;
import com.tfriends.service.CmsService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class V1Controller {

    @Autowired
    private CmsService service;

    public String directCommunity(String category, Object no) {
        String direct = "";
        if (no != null) {
            direct = "/community/" + category + "/read/" + no;
        }

        return direct;
    }

    @GetMapping("/community/{category}")
    public ModelAndView defaultBoard(Model mdl, PaginationDTO page,
            @PathVariable("category") String board,
            SearchDTO dto) {
        dto.setBoard(board);

        page.setCount(dto);
        page.setTotalpage(service.userBoardCount(dto));

        if (page.getEnd() < dto.getPage()) {
            dto.setPage(page.getEnd());
        }

        if (dto.getPage() <= 0) {
            dto.setPage(1);
        }

        mdl.addAttribute("dto", dto);
        mdl.addAttribute("page", page);

        return new ModelAndView("/cms/default/list", "board",
                service.userBoardList(dto));
    }

    // Common Area
    @GetMapping("/community/{category}/read/{arcno}")
    public String defaultBoardArticle(@PathVariable("category") String c, Model mdl, @PathVariable("arcno") int no,
            @ModelAttribute("dto") SearchDTO dto) {
        mdl.addAttribute("read", service.userBoardRead(c, no));
        return "/cms/default/read";
    }

    @PostMapping("/community/{category}/edit/{arcno}")
    public ModelAndView defaultBoardEdit(@PathVariable("category") String c,
            Model mdl, @PathVariable("arcno") int no,
            @ModelAttribute("dto") SearchDTO dto) {
        mdl.addAttribute("edit", service.userBoardRead(c, no));
        return new ModelAndView("/cms/default/write", "isEdit", 1);
    }

    @PostMapping("/community/{category}/edit/{arcno}/go")
    public String defaultBoardEdit(@PathVariable("category") String c,
            @PathVariable("arcno") int no, DefaultDTO cms,
            SearchDTO dto) {
        AccountDTO accounts = this.getAuthen();
        cms.setNo(no);
        cms.setWriter(accounts.getUno());

        service.editArticle(c, cms);
        String rebor = "redirect:" + directCommunity(c, no) +
                dto.uriQuerys(dto.getPage());
        return rebor;
    }

    @GetMapping("/community/{category}/write")
    public ModelAndView defaultBoardArticle(@PathVariable("category") String c,
            @ModelAttribute("dto") SearchDTO dto) {
        return new ModelAndView("/cms/default/write", "isEdit", 0);
    }

    @PostMapping("/community/{category}/write")
    public String defaultBoardArticleGo(@PathVariable("category") String c,
            DefaultDTO dto) {
        AccountDTO accounts = this.getAuthen();

        PermissionDTO permissionWrite = service.getBoardPermission("community", c);

        if (accounts.getGrade() >= permissionWrite.getPermissionWrite()) {
            dto.setWriter(accounts.getUno());
            service.regArticle(c, dto);
        } else {
            System.out.println(accounts.getUno() + "번 유저, 부정행위 감지.");
        }

        return "redirect:/community/" + c;
    }

    // Standard Area
    @PostMapping("/community/{category}/delete/{rno}")
    public String defaultBoardDelete(@PathVariable("category") String category,
            @PathVariable("rno") int rno,
            HttpServletRequest req) throws Exception {
        AccountDTO a = this.getAuthen();

        String ipRequest = req.getHeader("CF-Connecting-IP");
        if (ipRequest == null) {
            ipRequest = req.getRemoteAddr();
        }

        DefaultDTO article = service.userBoardRead(category, rno);
        if (a.getUno() == article.getWriter()) {
            deleteArticleThrow(a, ipRequest, category, rno, article);

            return "redirect:/community/" + category;
        }
        return "redirect:/warning";
    }

    // List Area
    @PostMapping("/community/{category}/delete")
    public String defaultBoardDelete4Admin(@PathVariable("category") String category,
            @RequestParam("checkdelete") List<Integer> checkdelete, HttpServletRequest req) throws Exception {
        AccountDTO a = this.getAuthen();

        String ipRequest = req.getHeader("CF-Connecting-IP");
        if (ipRequest == null) {
            ipRequest = req.getRemoteAddr();
        }

        if (a.getRoles().equals("ROLE_ADMIN")) {
            for (int rno : checkdelete) {
                DefaultDTO article = service.userBoardRead(category, rno);

                deleteArticleThrow(a, ipRequest, category, rno, article);
            }

            return "redirect:/community/" + category;
        } else {
            return "redirect:/warning";
        }
    }

    public void deleteArticleThrow(AccountDTO a, String ipRequest, String category, int rno, DefaultDTO article)
            throws Exception {
        ObjectMapper obj = new ObjectMapper();
        String body = obj.writeValueAsString(article);

        TrashDTO trash = new TrashDTO(a.getUno(), a.getMail(), ipRequest, body);

        service.moveTrashcan(trash);
        service.deleteArticle(category, rno);
    }

    // Q&A Category
    @GetMapping("/qna/{category}")
    public String qaBoard(@PathVariable("category") String c) {
        return "/cms/qna";
    }

    @PostMapping("/{type}/new")
    public String newPost(@PathVariable("type") String type, String catetory) {
        return "redirect:" + type + "/" + catetory;
    }

    public AccountDTO getAuthen() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return (AccountDTO) auth.getPrincipal();
    }
}
