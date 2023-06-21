package com.tfriends.root.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.dto.pagination.SearchDTOV2;
import com.tfriends.service.CmsServiceV2;

@Controller
public class V2Controller {

    @Autowired
    private CmsServiceV2 service;

    @GetMapping("/cmsv2/{hash}")
    public Object defaultBoard(PaginationDTOV2 page, Model mdl, @PathVariable("hash") String hash, SearchDTOV2 dto) {
        try {
            SecureDTO secure = service.hashCheck(hash);
            String board = secure.getType() + "_" + secure.getUrl();

            page.setCount(dto);
            page.setTotalpage(service.userBoardCount(board, dto));

            if (page.getEnd() < dto.getPage()) {
                dto.setPage(page.getEnd());
            }

            if (dto.getPage() <= 0) {
                dto.setPage(1);
            }

            mdl.addAttribute("dto", dto);
            mdl.addAttribute("page", page);

            // mdl.addAttribute("detail", secure);
            mdl.addAttribute("board", service.userBoardList(board, dto));

            return "/cms/default/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "/cms/default/list";
        }
    }

    @GetMapping("/cmsv2/{hash}/read")
    public String defaultBoardRead(@PathVariable("hash") String hash) {

        if (hash == null) {

        }

        return "/cms/default/read";
    }
}
