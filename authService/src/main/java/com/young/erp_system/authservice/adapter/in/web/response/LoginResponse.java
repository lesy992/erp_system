package com.young.erp_system.authservice.adapter.in.web.response;

import com.young.erp_system.common.jwt.JwtToken;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;

    private String tokenType;

    private long expiresIn;

    public static LoginResponse from(JwtToken token) {
        return new LoginResponse(token.accessToken(), token.tokenType(), token.expiresIn());
    }
}
