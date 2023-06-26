package com.tfriends.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfriends.dao.HomeDAO;
import com.tfriends.dto.system.MenuDTO;

@Service
public class HomeService {
    
    @Autowired
    private HomeDAO dao;

    public List<MenuDTO> homeMenu() {
        return dao.menuLoad();
    }

    public String getLoginURL() {
        return dao.subURL();
    }
}
