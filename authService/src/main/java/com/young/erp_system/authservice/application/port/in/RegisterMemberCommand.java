package com.young.erp_system.authservice.application.port.in;

import com.young.erp_system.authservice.domain.DelYn;
import com.young.erp_system.authservice.domain.Role;
import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterMemberCommand extends SelfValidating<RegisterMemberCommand> {

    @NotNull
    private final String name;

    @NotNull
    private final String address;

    @NotNull
    private final String email;

    @NotNull
    private final String password;

    @NotNull
    private final DelYn delYn;

    @NotNull
    private final Role role;

    public RegisterMemberCommand(String name, String address, String email, String password, DelYn delYn, Role role) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.delYn = delYn;
        this.role = role;

        this.validateSelf();
    }
}
