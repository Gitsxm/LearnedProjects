package com.example.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.jwt.domain.User;

import java.util.Date;

/**
 * @ClassName JwtUtils
 * @Description TODO
 * @Author MGG
 * @Date 2020/3/25 15:22
 * @Version 1.0
 */
public class JwtUtils {
    private static final String ISSUER = "user";//token 类型
    private static final Integer EXPIRATION_TIME = 5 * 60 * 1000;// 过期时间

    /**
     * 生成 Token
     *
     * @param user
     * @return
     */
    public static String createToken(User user) {
        if (user == null)
            throw new RuntimeException("用户信息不能为空！");
        Algorithm algorithm = Algorithm.HMAC256(user.getPassWord());
        JWTCreator.Builder builder = JWT.create()
                .withAudience(user.getUserId())
                .withIssuer(ISSUER)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        return builder.sign(algorithm);
    }

    /**
     * 验证token
     *
     * @param token
     * @param user
     */
    public static void verifyToken(String token, User user) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassWord())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("签名已过期或者不正确，请重新登录");
        }
    }
}
