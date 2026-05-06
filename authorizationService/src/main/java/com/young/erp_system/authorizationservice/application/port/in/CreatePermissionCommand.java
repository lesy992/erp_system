package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CreatePermissionCommand extends SelfValidating<CreatePermissionCommand> {

    @NotBlank private final String resource;
    @NotBlank private final String action;
    private final String description;

    @Builder
    public CreatePermissionCommand(String resource, String action, String description) {
        this.resource = resource;
        this.action = action;
        this.description = description;
        this.validateSelf();
    }
}
