package com.young.erp_system.common.utils;


import com.young.erp_system.common.context.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtProvider {

    private final Key key = Keys.hmacShaKeyFor("your-256-bit-secret-key-is-required-here".getBytes());

    // 토큰 생성 (Auth Service에서 사용)
    public String createToken(UserContext user) {
        return Jwts.builder()
                .setSubject(user.getUserId())
                .claim("tenantId", user.getTenantId())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1시간
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 토큰 해석 (Gateway 및 모든 Biz Service에서 사용)
    public UserContext parseToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return UserContext.builder()
                .userId(claims.getSubject())
                .tenantId(claims.get("tenantId", String.class))
                .role(claims.get("role", String.class))
                .build();
    }
}
