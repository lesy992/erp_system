package com.young.erp_system.authservice.application.service;

import com.young.erp_system.authservice.infrastructure.jwt.JwtProvider;
import io.jsonwebtoken.Claims;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.HexFormat;

@Component
public class RedisTokenBlacklistService {

    private static final String BLACKLIST_PREFIX = "blacklist:access-token:";

    private final StringRedisTemplate redisTemplate;
    private final JwtProvider jwtProvider;

    public RedisTokenBlacklistService(StringRedisTemplate redisTemplate, JwtProvider jwtProvider) {
        this.redisTemplate = redisTemplate;
        this.jwtProvider = jwtProvider;
    }

    public void blacklist(String token) {
        Claims claims = jwtProvider.parseClaims(token);
        long ttlMillis = claims.getExpiration().getTime() - System.currentTimeMillis();

        if (ttlMillis <= 0) {
            return;
        }

        redisTemplate.opsForValue().set(
                buildKey(token),
                "logout",
                Duration.ofMillis(ttlMillis)
        );
    }

    public boolean isBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(buildKey(token)));
    }

    private String buildKey(String token) {
        return BLACKLIST_PREFIX + sha256(token);
    }

    private String sha256(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm is not available", e);
        }
    }
}
