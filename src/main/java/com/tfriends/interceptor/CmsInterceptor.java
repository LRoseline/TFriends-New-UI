package com.tfriends.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tfriends.dto.system.PermissionDTO;
import com.tfriends.service.CmsService;

// import com.tfriends.dto.system.PermissionDTO;
// import com.tfriends.service.CmsServiceV2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CmsInterceptor implements HandlerInterceptor {

    @Autowired
    private CmsService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String[] uri = request.getRequestURI().split("/");

        PermissionDTO dto = service.getBoardPermission(uri[1], uri[2]);

        String objectBoard = "notice";
        Object[] object = { service.getBoardPermission("community", objectBoard),
        service.shortCommunityBoard(objectBoard) };
        request.setAttribute("echo", object);

        request.setAttribute("permission", dto);
        request.setAttribute("uriparameter", uri);

        return true;
    }
}
