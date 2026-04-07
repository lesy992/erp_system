package com.young.erp_system.authservice.infrastructure.jwt;

public record JwtToken (
        String accessToken,
        String tokenType,
        long expiresIn
){
}
