package com.tfriends.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tfriends.dto.AccountDTO;
import com.tfriends.service.AccountService;
import com.tfriends.service.HomeService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeInterceptor implements HandlerInterceptor {

    @Autowired
    private HomeService service;

    @Autowired
    private AccountService accounts;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("always", service.homeMenu());
        request.setAttribute("login", service.getLoginURL());
        request.setAttribute("fulluriwithout", request.getRequestURI());
        request.setAttribute("fulluri", request.getRequestURL()+this.getQuery(request));

        AccountDTO dto;

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            dto = (AccountDTO) auth.getPrincipal();
            dto = accounts.getLogin(dto.getUno());
        } catch (Exception e) {
            dto = null;
        }

        request.setAttribute("authen", dto);

        return true;
    }

    public String getQuery(HttpServletRequest req) {
        String query = req.getQueryString();

        if (query != null) {
            return "?"+query;
        }

        return "";
    }
}
