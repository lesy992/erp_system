package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CheckAuthorizationCommand extends SelfValidating<CheckAuthorizationCommand> {

    @NotBlank
    private final String role;

    @NotBlank
    private final String resource;

    @NotBlank
    private final String action;
}
