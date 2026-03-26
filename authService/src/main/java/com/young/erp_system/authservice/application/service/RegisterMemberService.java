package com.young.erp_system.authservice.application.service;

import com.young.erp_system.authservice.adapter.out.persistence.MemberJpaEntity;
import com.young.erp_system.authservice.adapter.out.persistence.MemberMapper;
import com.young.erp_system.authservice.application.port.in.RegisterMemberCase;
import com.young.erp_system.authservice.application.port.in.RegisterMemberCommand;
import com.young.erp_system.authservice.application.port.out.RegisterMemberPort;
import com.young.erp_system.authservice.domain.Member;
import common.MemberCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@MemberCase
@RequiredArgsConstructor
@Transactional
public class RegisterMemberService implements RegisterMemberCase {

    private final RegisterMemberPort registerMemberPort;

    private final PasswordEncoder passwordEncoder;

    private final MemberMapper memberMapper;

    @Override
    public Member registerMember(RegisterMemberCommand command) {

        MemberJpaEntity jpaEntity = registerMemberPort.createMember(
                new Member.MemberName(command.getName()),
                new Member.MemberAddress(command.getAddress()),
                new Member.MemberEmail(command.getEmail()),
                new Member.MemberPassword(passwordEncoder.encode(command.getPassword())),
                new Member.MemberDelYn(command.getDelYn()),
                new Member.MemberRole(command.getRole())
        );

        return memberMapper.mapToDomainEntity(jpaEntity);
    }
}
