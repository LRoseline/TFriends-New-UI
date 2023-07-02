package com.tfriends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tfriends.dao.SystemDAO;
import com.tfriends.dto.AccountDTO;
import com.tfriends.dto.system.menu.MenuDTOv2;

@Service
public class SystemService {
    
    @Autowired
    private SystemDAO dao;

    public List<MenuDTOv2> newMenu() {
        return dao.menuLoad();
    }

    public String getLoginURL() {
        return dao.subURL();
    }

    public AccountDTO getAuthen() {
        AccountDTO auth = (AccountDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return dao.loginInfo(auth.getUno());
    }
}
