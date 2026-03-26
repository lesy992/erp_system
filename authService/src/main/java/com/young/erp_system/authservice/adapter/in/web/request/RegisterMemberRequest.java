package com.young.erp_system.authservice.adapter.in.web.request;

import com.young.erp_system.authservice.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterMemberRequest {

    private String name;

    private String address;

    private String email;

    private String password;

    private Role role;
}
