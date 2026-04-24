package com.young.erp_system.authorizationservice.infrastructure.jwt;

import com.young.erp_system.common.jwt.JwtProviderBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider extends JwtProviderBase {

    public JwtProvider(@Value("${jwt.secret}") String secret) {
        super(secret);
    }
}
