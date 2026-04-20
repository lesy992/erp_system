package com.young.erp_system.authservice.infrastructure.jwt;

import com.young.erp_system.authservice.application.service.RedisTokenBlacklistService;
import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    private final JwtProvider jwtProvider;
    private final RedisTokenBlacklistService tokenBlacklistService;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, RedisTokenBlacklistService tokenBlacklistService) {
        this.jwtProvider = jwtProvider;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return PATH_MATCHER.match("/api/auth/login", uri)
                || PATH_MATCHER.match("/api/auth/signup", uri)
                || PATH_MATCHER.match("/api/auth/logout", uri)
                || PATH_MATCHER.match("/swagger-ui/**", uri)
                || PATH_MATCHER.match("/v3/api-docs/**", uri)
                || PATH_MATCHER.match("/swagger-ui.html", uri);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || authHeader.isBlank()) {
            unauthorized(response, ErrorCode.AUTH_FAILED);
            return;
        }

        try {
            if (tokenBlacklistService.isBlacklisted(authHeader)) {
                unauthorized(response, ErrorCode.TOKEN_BLACKLISTED);
                return;
            }

            Claims claims = jwtProvider.parseClaims(authHeader);
            String role = claims.get("role", String.class);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    claims.getSubject(),
                    null,
                    List.of(new SimpleGrantedAuthority(role))
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            unauthorized(response, e.getErrorCode());
        }
    }

    private void unauthorized(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getStatus());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(
                String.format("{\"status\":%d,\"code\":\"%s\",\"message\":\"%s\"}",
                        errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage())
        );
    }
}
