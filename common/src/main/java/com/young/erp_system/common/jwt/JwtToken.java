package com.young.erp_system.common.jwt;

public record JwtToken(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}
