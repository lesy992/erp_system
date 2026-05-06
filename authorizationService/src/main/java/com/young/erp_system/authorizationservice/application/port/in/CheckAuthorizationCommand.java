package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CheckAuthorizationCommand extends SelfValidating<CheckAuthorizationCommand> {

    @NotBlank private final String role;
    @NotBlank private final String resource;
    @NotBlank private final String action;

    @Builder
    public CheckAuthorizationCommand(String role, String resource, String action) {
        this.role = role;
        this.resource = resource;
        this.action = action;
        this.validateSelf();
    }
}
