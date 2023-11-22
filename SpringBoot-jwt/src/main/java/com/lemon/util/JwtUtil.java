package com.lemon.util;

import com.alibaba.fastjson.JSON;
import com.lemon.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JWT工具类
 */
@Component
public class JwtUtil {
    //key的大小必须大于等于256 bits，一个英文字符为 8bit，最少32个英文字符
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    //设置加密算法
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    /**
     * 获取转换后的私钥对象
     */
    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    /**
     * 将user作为token加密
     */
    public String generateToken(User user) {
        System.out.println(secret + " " + expiration);
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        String userJson = JSON.toJSONString(user);

        return Jwts.builder()
                //头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //载荷
                .claim("user", userJson)
                //有效时间 从当前时间开始，持续一天
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                //签名
                .signWith(getSecretKey(), signatureAlgorithm)
                .compact();
    }

    /**
     * 解码token
     */
    public User getUserFromTokne(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        //Json反序列化
        String userJson = claims.get("user", String.class);

        return JSON.parseObject(userJson, User.class);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
