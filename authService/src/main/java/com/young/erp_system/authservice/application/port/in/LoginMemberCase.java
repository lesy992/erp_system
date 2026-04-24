package com.young.erp_system.authservice.application.port.in;

import com.young.erp_system.common.jwt.JwtToken;

public interface LoginMemberCase {

    JwtToken loginMember(LoginMemberCommand command);
}
