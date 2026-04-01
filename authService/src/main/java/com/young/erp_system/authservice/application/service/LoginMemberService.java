package com.young.erp_system.authservice.application.service;

import com.young.erp_system.authservice.adapter.out.persistence.MemberJpaEntity;
import com.young.erp_system.authservice.application.port.in.LoginMemberCase;
import com.young.erp_system.authservice.application.port.in.LoginMemberCommand;
import com.young.erp_system.authservice.application.port.out.LoginMemberPort;
import com.young.erp_system.authservice.infrastructure.jwt.JwtProvider;
import common.MemberCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@MemberCase
@RequiredArgsConstructor
@Transactional
public class LoginMemberService implements LoginMemberCase {

    private final LoginMemberPort loginMemberPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

}
