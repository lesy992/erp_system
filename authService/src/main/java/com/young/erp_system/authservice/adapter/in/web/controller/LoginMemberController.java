package com.young.erp_system.authservice.adapter.in.web.controller;

import com.young.erp_system.authservice.adapter.in.web.request.LoginMemberRequest;
import com.young.erp_system.authservice.adapter.in.web.response.LoginResponse;
import com.young.erp_system.authservice.application.port.in.LoginMemberCase;
import com.young.erp_system.authservice.application.port.in.LoginMemberCommand;
import common.WebAdapter;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginMemberRequest request){
        LoginMemberCommand command = LoginMemberCommand.builder()
                .email(request.getMemberEmail())
                .password(request.getMemberPassword())
                .build();

        String token = loginMemberCase.loginMember(command);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
