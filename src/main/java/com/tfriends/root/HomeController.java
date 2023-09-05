package com.tfriends.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tfriends.dto.index.HomeDTO;
import com.tfriends.dto.index.Option;
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
        Option op = dto.getTag();
        model.addAttribute("welcome", dto);

        if (dto.getValue().equals("1")) {
            int no = Integer.parseInt(op.getOption3());
            model.addAttribute("board", cms.readInIndex(op.getOption2(), no));
        }

        return "default/home";
    }
}
