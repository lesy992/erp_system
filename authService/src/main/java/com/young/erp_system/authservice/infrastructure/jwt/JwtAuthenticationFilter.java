package com.young.erp_system.authservice.infrastructure.jwt;

import com.young.erp_system.authservice.application.service.RedisTokenBlacklistService;
import com.young.erp_system.common.jwt.AbstractJwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends AbstractJwtAuthenticationFilter {

    public JwtAuthenticationFilter(JwtProvider jwtProvider, RedisTokenBlacklistService tokenBlacklistService) {
        super(jwtProvider, tokenBlacklistService);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return super.shouldNotFilter(request)
                || PATH_MATCHER.match("/api/auth/login", uri)
                || PATH_MATCHER.match("/api/auth/signup", uri)
                || PATH_MATCHER.match("/api/auth/logout", uri);
    }
}
