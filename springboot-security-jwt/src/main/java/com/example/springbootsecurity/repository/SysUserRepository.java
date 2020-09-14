package com.example.springbootsecurity.repository;

import com.example.springbootsecurity.system.authorities.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser,Long> {
    SysUser findSysUserByUsername(String name);
}
