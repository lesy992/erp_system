package com.young.erp_system.authservice.adapter.in.web.controller;

import com.young.erp_system.authservice.adapter.in.web.request.RegisterMemberRequest;
import com.young.erp_system.authservice.application.port.in.RegisterMemberCase;
import com.young.erp_system.authservice.application.port.in.RegisterMemberCommand;
import com.young.erp_system.authservice.domain.DelYn;
import com.young.erp_system.authservice.domain.Member;
import com.young.erp_system.common.annotation.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegisterMemberController {

    private final RegisterMemberCase registerMemberCase;

    @PostMapping("/signup")
    Member registerMember(@RequestBody RegisterMemberRequest request) {
        RegisterMemberCommand command = RegisterMemberCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .password(request.getPassword())
                .delYn(DelYn.DEL_N)
                .role(request.getRole())
                .build();

        return registerMemberCase.registerMember(command);
    }


}
