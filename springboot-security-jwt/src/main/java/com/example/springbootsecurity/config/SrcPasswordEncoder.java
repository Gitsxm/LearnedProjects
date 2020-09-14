package com.example.springbootsecurity.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义了一个密码加密方式，保持明文
 *
 */
public class SrcPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
