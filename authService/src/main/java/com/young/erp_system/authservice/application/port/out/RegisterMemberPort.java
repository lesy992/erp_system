package com.young.erp_system.authservice.application.port.out;

import com.young.erp_system.authservice.adapter.out.persistence.MemberJpaEntity;
import com.young.erp_system.authservice.domain.Member;

public interface RegisterMemberPort {

    MemberJpaEntity createMember(
            Member.MemberName memberName
            , Member.MemberAddress memberAddress
            , Member.MemberEmail memberEmail
            , Member.MemberPassword memberPassword
            , Member.MemberDelYn memberDelYn
            , Member.MemberRole memberRole
    );

}
