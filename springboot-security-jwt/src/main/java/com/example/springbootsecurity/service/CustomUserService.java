package com.example.springbootsecurity.service;

import com.example.springbootsecurity.repository.SysUserRepository;
import com.example.springbootsecurity.system.authorities.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户验证
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user =  sysUserRepository.findSysUserByUsername(s);
        if (null == user)
            throw new UsernameNotFoundException("username "+ s +" not found!");
        return user;
    }
}
