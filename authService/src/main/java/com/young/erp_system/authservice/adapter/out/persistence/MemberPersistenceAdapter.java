package com.young.erp_system.authservice.adapter.out.persistence;

import com.young.erp_system.authservice.application.port.out.LoginMemberPort;
import com.young.erp_system.authservice.application.port.out.RegisterMemberPort;
import com.young.erp_system.authservice.domain.Member;
import com.young.erp_system.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements RegisterMemberPort, LoginMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public MemberJpaEntity createMember(Member.MemberName memberName, Member.MemberAddress memberAddress, Member.MemberEmail memberEmail, Member.MemberPassword memberPassword, Member.MemberDelYn memberDelYn, Member.MemberRole memberRole) {
        return memberRepository.save(
            new MemberJpaEntity(
                memberName.getMemberName()
                ,memberAddress.getMemberAddress()
                ,memberEmail.getMemberEmail()
                ,memberPassword.getMemberPassword()
                ,memberDelYn.getMemberDelYn()
                ,memberRole.getMemberRole()
            )
        );
    }

    @Override
    public Optional<MemberJpaEntity> findByEmail(Member.MemberEmail memberEmail, Member.MemberDelYn memberDelYn) {
        return memberRepository.findByMemberEmail(memberEmail.getMemberEmail());
    }

}
