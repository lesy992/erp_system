package com.young.erp_system.authservice.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;
}
