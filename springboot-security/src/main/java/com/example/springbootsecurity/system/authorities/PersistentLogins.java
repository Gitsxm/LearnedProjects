package com.example.springbootsecurity.system.authorities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户登录记录
 */
@Entity
@Table(name = "persistent_logins")
@Data
@ToString
public class PersistentLogins implements Serializable {

    @Id
    @Column(name = "series",length = 64,nullable = false)
    private String series;
    @Column(name = "username",length = 64,nullable = false)
    private String username;
    @Column(name = "token",length = 64,nullable = false)
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_used",length = 64,nullable = false)
    private Date last_used;

}
