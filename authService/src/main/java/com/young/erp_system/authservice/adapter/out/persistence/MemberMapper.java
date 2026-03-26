package com.young.erp_system.authservice.adapter.out.persistence;

import com.young.erp_system.authservice.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member mapToDomainEntity(MemberJpaEntity memberJpaEntity){
        return Member.generatMember(
                new Member.MemberId(memberJpaEntity.getMemberId())
                , new Member.MemberName(memberJpaEntity.getMemberName())
                ,new Member.MemberAddress(memberJpaEntity.getMemberAddress())
                ,new Member.MemberEmail(memberJpaEntity.getMemberEmail())
                ,new Member.MemberPassword(memberJpaEntity.getMemberPassword())
                ,new Member.MemberDelYn(memberJpaEntity.getDelYn())
                ,new Member.MemberRole(memberJpaEntity.getRole())
        );
    }
}
