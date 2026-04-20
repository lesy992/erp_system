package com.young.erp_system.authservice.adapter.in.web.controller;

import com.young.erp_system.authservice.adapter.in.web.request.LoginMemberRequest;
import com.young.erp_system.authservice.adapter.in.web.request.TokenValidationRequest;
import com.young.erp_system.authservice.adapter.in.web.response.LoginResponse;
import com.young.erp_system.authservice.adapter.in.web.response.TokenValidationResponse;
import com.young.erp_system.authservice.application.port.in.LoginMemberCase;
import com.young.erp_system.authservice.application.port.in.LoginMemberCommand;
import com.young.erp_system.authservice.application.service.RedisTokenBlacklistService;
import com.young.erp_system.authservice.infrastructure.jwt.JwtProvider;
import com.young.erp_system.authservice.infrastructure.jwt.JwtToken;
import com.young.erp_system.common.annotation.WebAdapter;
import com.young.erp_system.common.exception.CustomException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@WebAdapter
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginMemberController {

    private static final String ACCESS_TOKEN_COOKIE_NAME = "accessToken";
    private static final String BEARER_PREFIX = "Bearer ";

    private final LoginMemberCase loginMemberCase;
    private final JwtProvider jwtProvider;
    private final RedisTokenBlacklistService tokenBlacklistService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginMemberRequest request) {
        LoginMemberCommand command = LoginMemberCommand.builder()
                .email(request.getMemberEmail())
                .password(request.getMemberPassword())
                .build();

        JwtToken token = loginMemberCase.loginMember(command);

        //return ResponseEntity.ok(LoginResponse.from(token));
        ResponseCookie accessTokenCookie = ResponseCookie.from(ACCESS_TOKEN_COOKIE_NAME, token.accessToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(token.expiresIn() / 1000)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok()
                .header("Set-Cookie", accessTokenCookie.toString())
                .body(LoginResponse.from(token));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request){
        extractToken(request).ifPresent(token -> {
                    try {
                        tokenBlacklistService.blacklist(token);
                    } catch (CustomException ignored) {
                        // 로그아웃 API는 멱등하게 처리: 이미 만료/잘못된 토큰이어도 쿠키 삭제는 수행
                    }
                });

        ResponseCookie deleteAccessTokenCookie = ResponseCookie.from(ACCESS_TOKEN_COOKIE_NAME, "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        return ResponseEntity.noContent()
                .header("Set-Cookie", deleteAccessTokenCookie.toString())
                .build();
    }

    @PostMapping("/token/validate")
    public ResponseEntity<TokenValidationResponse> validateToken(@RequestBody TokenValidationRequest request){
        Claims claims = jwtProvider.parseClaims(request.getToken());

        TokenValidationResponse response = new TokenValidationResponse(
                true,
                claims.getSubject(),
                claims.get("role", String.class),
                claims.getIssuedAt(),
                claims.getExpiration()
        );
        return ResponseEntity.ok(response);
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && !authHeader.isBlank()) {
            return Optional.of(authHeader);
        }

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }

        return Arrays.stream(cookies)
                .filter(cookie -> ACCESS_TOKEN_COOKIE_NAME.equals(cookie.getName()))
                .map(Cookie::getValue)
                .filter(value -> value != null && !value.isBlank())
                .map(value -> BEARER_PREFIX + value)
                .findFirst();
    }

}
