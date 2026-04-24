package com.young.erp_system.common.jwt;

import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public abstract class JwtProviderBase {

    protected static final String TOKEN_PREFIX = "Bearer ";

    protected final Key secretKey;

    protected JwtProviderBase(String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims parseClaims(String token) {
        try {
            String plainToken = stripBearerPrefix(token);
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(plainToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.AUTH_FAILED);
        }
    }

    public long getRemainingMillis(Claims claims) {
        return claims.getExpiration().getTime() - System.currentTimeMillis();
    }

    private String stripBearerPrefix(String token) {
        if (token == null) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
        }
        if (token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length());
        }
        return token;
    }
}
