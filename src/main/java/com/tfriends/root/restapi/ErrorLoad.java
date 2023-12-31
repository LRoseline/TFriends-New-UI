package com.tfriends.root.restapi;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfriends.dto.cms.SecureDTO;
import com.tfriends.dto.system.ErrorDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RestController
public class ErrorLoad implements ErrorController {

    @PostMapping("/error")
    public ResponseEntity<SecureDTO> errorAPI(HttpServletRequest req) {
        int cod = Integer.valueOf(req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
        SecureDTO dto = new SecureDTO();
        dto.setStatus(new ErrorDTO(cod));

        return new ResponseEntity<SecureDTO>(dto, HttpStatusCode.valueOf(cod));
    }
}
