package com.young.erp_system.authservice.application.port.in;

import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class LoginMemberCommand extends SelfValidating<LoginMemberCommand> {

    @NotBlank private final String email;
    @NotBlank private final String password;

    @Builder
    public LoginMemberCommand(String email, String password) {
        this.email = email;
        this.password = password;
        this.validateSelf();
    }
}
