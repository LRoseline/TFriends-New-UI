package com.tfriends.root;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @GetMapping("/profile")
    public String profileView() {
        return "accounts/profile";
    }

    @PostMapping("/logout")
    public String logOut() {
        return "redirect:/";
    }

    public static String checkBanned(int uno, String url) {
        String redirectBan = url;
        if (uno == 0) {
            redirectBan = "redirect:/banned";
        }

        return redirectBan;
    }
}