package com.tfriends.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tfriends.dto.index.HomeDTO;
import com.tfriends.service.ShortService;
import com.tfriends.service.SystemService;

@Controller
public class HomeController {

    @Autowired
    private SystemService s;

    @Autowired
    private ShortService cms;

    @GetMapping("/")
    public String home(Model model) {
        HomeDTO dto = s.optionDetail("homescreen");
        model.addAttribute("welcome", dto);

        if (dto.getValue().equals("1")) {
            model.addAttribute("board",
                    cms.readInIndex(dto.getTag().getOption2(), Integer.valueOf(dto.getTag().getOption3())));
        }

        return "default/home";
    }
}
