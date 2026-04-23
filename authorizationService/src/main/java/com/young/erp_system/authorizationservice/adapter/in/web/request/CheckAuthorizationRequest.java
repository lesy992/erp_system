package com.young.erp_system.authorizationservice.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckAuthorizationRequest {

    private String role;
    private String resource;
    private String action;
}
