package com.young.erp_system.authservice.application.service;

import com.young.erp_system.authservice.adapter.out.persistence.MemberJpaEntity;
import com.young.erp_system.authservice.application.port.in.LoginMemberCase;
import com.young.erp_system.authservice.application.port.in.LoginMemberCommand;
import com.young.erp_system.authservice.application.port.out.LoginMemberPort;
import com.young.erp_system.authservice.domain.DelYn;
import com.young.erp_system.authservice.domain.Member;
import com.young.erp_system.authservice.infrastructure.jwt.JwtProvider;
import com.young.erp_system.authservice.infrastructure.jwt.JwtToken;
import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
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

    @Override
    public JwtToken loginMember(LoginMemberCommand command) {

        MemberJpaEntity member = loginMemberPort.findByEmail(
                new Member.MemberEmail(command.getEmail()),
                        new Member.MemberDelYn(DelYn.DEL_N))
                .orElseThrow(() -> new CustomException(ErrorCode.AUTH_FAILED));

        if(!passwordEncoder.matches(command.getPassword(), member.getMemberPassword())){
            throw new CustomException(ErrorCode.AUTH_FAILED);
        }

        return jwtProvider.createAccessToken(member.getMemberEmail(), member.getMemberRole().name());
    }
}
