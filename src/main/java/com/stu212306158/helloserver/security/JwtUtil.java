package com.stu212306158.helloserver.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.expiration-millis}")
    private long expirationMillis;

    // 获取签名密钥
    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    // 生成JWT令牌
    public String generateToken(String username) {
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + expirationMillis);
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expireTime)
                .signWith(getSignKey())
                .compact();
    }

    // 解析Token获取用户名
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }
}