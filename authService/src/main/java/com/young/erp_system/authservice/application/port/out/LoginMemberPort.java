package com.young.erp_system.authservice.application.port.out;

import com.young.erp_system.authservice.adapter.out.persistence.MemberJpaEntity;
import com.young.erp_system.authservice.domain.Member;

import java.util.Optional;

public interface LoginMemberPort {
    Optional<MemberJpaEntity> findByEmail(Member.MemberEmail memberEmail, Member.MemberDelYn memberDelYn);
}
