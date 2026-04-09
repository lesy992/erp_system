package com.young.erp_system.authservice.application.port.in;

import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class LoginMemberCommand extends SelfValidating<LoginMemberCommand> {

    @NotNull
    private final String email;

    @NotNull
    private final String password;
}
