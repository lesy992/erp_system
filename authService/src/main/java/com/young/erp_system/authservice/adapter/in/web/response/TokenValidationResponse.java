package com.young.erp_system.authservice.adapter.in.web.response;

import java.util.Date;

public record TokenValidationResponse(
        boolean valid,
        String email,
        String role,
        Date issuedAt,
        Date expiration) {
}
