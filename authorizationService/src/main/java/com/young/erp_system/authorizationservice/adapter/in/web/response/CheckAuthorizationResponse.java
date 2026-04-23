package com.young.erp_system.authorizationservice.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckAuthorizationResponse {

    private boolean allowed;
    private String role;
    private String resource;
    private String action;
}
