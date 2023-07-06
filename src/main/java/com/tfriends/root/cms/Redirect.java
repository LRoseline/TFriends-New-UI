package com.tfriends.root.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tfriends.service.CmsServiceV2;

@Controller
public class Redirect {
    @Autowired
    private CmsServiceV2 cms;

    @GetMapping("/community/{url}")
    public String redirectList(@PathVariable("url")String url) {
        return cms.redirectTo(url);
    }

    @GetMapping("/community/{url}/read/{arcno}")
    public String redirectRead(@PathVariable("url")String url, @PathVariable("arcno")int arcno) {
        return cms.redirectTo(url)+"/read/"+arcno;
    }
}
