package com.young.erp_system.authservice.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginMemberRequest {

    private String memberEmail;

    private String memberPassword;

}
