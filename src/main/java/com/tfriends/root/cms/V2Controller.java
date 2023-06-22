package com.tfriends.root.cms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.exception.board.BoardNotFoundException;
import com.tfriends.service.CmsServiceV2;

@Controller
public class V2Controller {

    @Autowired
    private CmsServiceV2 service;

    @GetMapping("/cmsv2/{hash}")
    public String defaultBoard(PaginationDTOV2 page, Model mdl, @PathVariable("hash") String hash) {
        SecureDTO result = service.userBoardList(hash, page);

        if (hash != null) {

            mdl.addAttribute("detail", result);

            return "/cms/default/listv2";
        } else {
            throw new BoardNotFoundException(hash);
        }
    }

    @GetMapping("/cmsv2/{hash}/read")
    public String defaultBoardRead(@PathVariable("hash") String hash) {

        if (hash == null) {

        }

        return "/cms/default/read";
    }
}
