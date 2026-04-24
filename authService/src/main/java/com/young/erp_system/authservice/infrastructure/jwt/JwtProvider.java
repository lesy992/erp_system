package com.young.erp_system.authservice.infrastructure.jwt;

import com.young.erp_system.common.jwt.JwtProviderBase;
import com.young.erp_system.common.jwt.JwtToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider extends JwtProviderBase {

    private final long accessTokenExpiration;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration_time}") long accessTokenExpiration) {
        super(secret);
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public JwtToken createAccessToken(String email, String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenExpiration);

        String accessToken = Jwts.builder()
                .setSubject(email)
                .setId(UUID.randomUUID().toString())
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return new JwtToken(accessToken, TOKEN_PREFIX, accessTokenExpiration / 1000);
    }
}
