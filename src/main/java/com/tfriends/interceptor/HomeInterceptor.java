package com.tfriends.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tfriends.dto.AccountDTO;
import com.tfriends.service.SystemService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeInterceptor implements HandlerInterceptor {

    @Autowired
    private SystemService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setAttribute("always", service.newMenu());
        request.setAttribute("login", service.getLoginURL());
        request.setAttribute("fulluriwithout", request.getRequestURI());
        request.setAttribute("fulluri", request.getRequestURL() + this.getQuery(request));

        AccountDTO dto;

        try {
            dto = service.getAuthen();
        } catch (Exception e) {
            dto = null;
        }

        request.setAttribute("authen", dto);

        return true;
    }

    public String getQuery(HttpServletRequest req) {
        String query = req.getQueryString();

        if (query != null) {
            return "?" + query;
        }

        return "";
    }
}
