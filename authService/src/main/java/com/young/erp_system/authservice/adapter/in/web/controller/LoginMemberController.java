package com.young.erp_system.authservice.adapter.in.web.controller;

import com.young.erp_system.authservice.adapter.in.web.request.LoginMemberRequest;
import com.young.erp_system.authservice.adapter.in.web.request.TokenValidationRequest;
import com.young.erp_system.authservice.adapter.in.web.response.LoginResponse;
import com.young.erp_system.authservice.adapter.in.web.response.TokenValidationResponse;
import com.young.erp_system.authservice.application.port.in.LoginMemberCase;
import com.young.erp_system.authservice.application.port.in.LoginMemberCommand;
import com.young.erp_system.authservice.infrastructure.jwt.JwtProvider;
import com.young.erp_system.authservice.infrastructure.jwt.JwtToken;
import common.WebAdapter;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginMemberController {

    private final LoginMemberCase loginMemberCase;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginMemberRequest request) {
        LoginMemberCommand command = LoginMemberCommand.builder()
                .email(request.getMemberEmail())
                .password(request.getMemberPassword())
                .build();

        JwtToken token = loginMemberCase.loginMember(command);

        return ResponseEntity.ok(LoginResponse.from(token));
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

}
