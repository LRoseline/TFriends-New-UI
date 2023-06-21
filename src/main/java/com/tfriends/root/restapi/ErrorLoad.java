package com.tfriends.root.restapi;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfriends.dto.system.ErrorDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RestController
public class ErrorLoad implements ErrorController {

    @PostMapping("/error")
    public ErrorDTO errorAPI(HttpServletRequest req) {
        ErrorDTO dto = new ErrorDTO();

        int cod = Integer.valueOf(req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());

        dto.setCod(cod);
        return dto;
    }
}
