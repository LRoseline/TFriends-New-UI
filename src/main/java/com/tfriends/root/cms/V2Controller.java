package com.tfriends.root.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tfriends.dto.cms.DefaultDTOv2;
import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.pagination.PaginationDTOV2;
import com.tfriends.dto.pagination.SearchDTOV2;
import com.tfriends.service.CmsServiceV2;
import com.tfriends.service.ShortService;

@Controller
public class V2Controller {

    @Autowired
    private CmsServiceV2 service;

    @Autowired
    private ShortService shorts;

    @GetMapping("/cmsv2/{hash}")
    public ModelAndView defaultBoard(PaginationDTOV2 page, @PathVariable("hash") String hash, Model mdl) {
        SecureDTO result = service.userBoardList(hash, page);
        mdl.addAttribute("echo", shorts.previewShorts());

        return new ModelAndView("/cms/default/listv2", "detail", result);
    }

    @GetMapping("/cmsv2/{hash}/read/{arcno}")
    public ModelAndView defaultBoardRead(@PathVariable("hash") String hash, @PathVariable("arcno") int no,
            PaginationDTOV2 page) {
        SecureDTO result = service.userBoardRead(hash, no, page);

        return new ModelAndView("/cms/default/readv2", "detail", result);
    }

    @GetMapping("/cmsv2/{hash}/write")
    public ModelAndView defaultBoardWritev2(@PathVariable("hash") String hash,
            @ModelAttribute("detail") PaginationDTOV2 page) {
        return new ModelAndView("/cms/default/writev2", "isEdit", 0);
    }

    @PostMapping("/cmsv2/{hash}/write")
    public String defaultBoardWritev2Go(@PathVariable("hash") String hash, DefaultDTOv2 dto) {
        service.regArticle(hash, dto);
        return "redirect:/cmsv2/" + hash;
    }

    @PostMapping("/cmsv2/{hash}/edit/{arcno}")
    public ModelAndView defaultBoardEditv2(Model mdl, @PathVariable("hash") String hash, PaginationDTOV2 page,
            @PathVariable("arcno") int no) {
        mdl.addAttribute("detail", service.userBoardRead(hash, no, page));
        return new ModelAndView("/cms/default/writev2", "isEdit", 1);
    }

    @PostMapping("/cmsv2/{hash}/edit/{arcno}/go")
    public String defaultBoardEdit(@PathVariable("hash") String hash, @PathVariable("arcno") int no, DefaultDTOv2 body,
            SearchDTOV2 dto) {
        service.editArticle(hash, body, no);
        return "redirect:/cmsv2/" + hash + "/read/" + no + dto.uriQuerys(dto.getPage());
    }
}
