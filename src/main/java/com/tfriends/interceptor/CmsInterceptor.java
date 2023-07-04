package com.tfriends.interceptor;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

// import com.tfriends.dto.system.PermissionDTO;
// import com.tfriends.service.CmsServiceV2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CmsInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String[] uri = request.getRequestURI().split("/");
        request.setAttribute("uriparameter", uri);

        return true;
    }
}
