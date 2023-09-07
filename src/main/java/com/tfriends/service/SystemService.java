package com.tfriends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tfriends.dao.AccountDAO;
import com.tfriends.dao.SystemDAO;
import com.tfriends.dto.AccountDTO;
import com.tfriends.dto.index.HomeDTO;
import com.tfriends.dto.system.bw.BwIpList;
import com.tfriends.dto.system.menu.MenuDTOv2;

@Service
public class SystemService {

    @Autowired
    private SystemDAO dao;

    @Autowired
    private AccountDAO ip;

    public List<MenuDTOv2> newMenu() {
        return dao.menuLoad();
    }

    public String getLoginURL() {
        return dao.subURL();
    }

    public HomeDTO optionDetail(String option) {
        return dao.indexHtmlShow(option);
    }

    public BwIpList ipBanned(String addr) {
        return ip.checkIP(addr);
    }

    public AccountDTO getAuthen() {
        AccountDTO auth = (AccountDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return dao.loginInfo(auth.getUno());
    }
}
