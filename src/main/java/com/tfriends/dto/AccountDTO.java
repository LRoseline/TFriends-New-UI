package com.tfriends.dto;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountDTO implements UserDetails {
    private static final long serialVersionUID = 1L;

    private long uno;
    private String name;
    private String mail;

    @JsonIgnore
    private String password;

    private Date joindate;

    private boolean certified;
    private Date certified2;
    private String ip;

    private long grade;
    private String roles;

    private String gravatar;
    private boolean accountNonExpired;
    private Date passwordExpired;
    private boolean accountNonLocked;
    private boolean enabled;

    @Override
    public String getUsername() {
        return getMail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(getRoles()));
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
