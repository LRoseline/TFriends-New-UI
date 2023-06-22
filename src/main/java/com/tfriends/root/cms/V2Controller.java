package com.tfriends.root.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.service.CmsServiceV2;

@Controller
public class V2Controller {

    @Autowired
    private CmsServiceV2 service;

    @GetMapping("/cmsv2/{hash}")
    public ModelAndView defaultBoard(PaginationDTOV2 page, @PathVariable("hash") String hash) {
        SecureDTO result = service.userBoardList(hash, page);

        return new ModelAndView("/cms/default/listv2", "detail", result);
    }

    @GetMapping("/cmsv2/{hash}/read")
    public String defaultBoardRead(@PathVariable("hash") String hash) {

        if (hash == null) {

        }

        return "/cms/default/read";
    }
}
