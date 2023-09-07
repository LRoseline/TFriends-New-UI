package com.tfriends.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tfriends.dto.system.bw.BwType;
import com.tfriends.service.SystemService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SecurityInterceptor implements HandlerInterceptor {

    @Autowired
    private SystemService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String ipRequest = request.getHeader("CF-Connecting-IP");
        if (ipRequest == null) {
            ipRequest = request.getRemoteAddr();
        }

        if (service.ipBanned(ipRequest).getType() == BwType.BLACK) {
            response.sendRedirect("/banned");
            return false;
        }

        return true;
    }

}
