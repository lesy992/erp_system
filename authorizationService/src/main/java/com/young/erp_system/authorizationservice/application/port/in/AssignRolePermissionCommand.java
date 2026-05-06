package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.authorizationservice.domain.Role;
import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class AssignRolePermissionCommand extends SelfValidating<AssignRolePermissionCommand> {

    @NotNull private final Role role;
    @NotNull private final Long permissionId;

    @Builder
    public AssignRolePermissionCommand(Role role, Long permissionId) {
        this.role = role;
        this.permissionId = permissionId;
        this.validateSelf();
    }
}
