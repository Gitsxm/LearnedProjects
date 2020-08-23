package com.example.springbootsecurity.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "securityconfig")
public class SecuritySettings {
    private String logoutSuccessUrl;
    private String permitAll;
    private String deniedPage;
    private String urlRoles;
}
