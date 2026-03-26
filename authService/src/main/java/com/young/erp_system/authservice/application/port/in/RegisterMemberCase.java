package com.young.erp_system.authservice.application.port.in;

import com.young.erp_system.authservice.domain.Member;

public interface RegisterMemberCase {

    Member registerMember(RegisterMemberCommand command);
}
