package com.young.erp_system.authorizationservice.infrastructure.jwt;

import com.young.erp_system.common.jwt.AbstractJwtAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends AbstractJwtAuthenticationFilter {

    public JwtAuthenticationFilter(JwtProvider jwtProvider, RedisTokenBlacklistService tokenBlacklistService) {
        super(jwtProvider, tokenBlacklistService);
    }
}
