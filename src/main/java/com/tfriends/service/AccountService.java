package com.tfriends.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tfriends.dao.AccountDAO;
import com.tfriends.dto.AccountDTO;

@Service
public class AccountService {

    @Autowired
    private AccountDAO dao;

    public AccountDTO getLogin(long uno) {
        return dao.loginInfo(uno);
    }

    public AccountDTO getAuthen() {
        AccountDTO auth = (AccountDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return dao.loginInfo(auth.getUno());
    }
}