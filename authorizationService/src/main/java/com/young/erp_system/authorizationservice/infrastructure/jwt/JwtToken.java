package com.young.erp_system.authorizationservice.infrastructure.jwt;

public record JwtToken(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}
