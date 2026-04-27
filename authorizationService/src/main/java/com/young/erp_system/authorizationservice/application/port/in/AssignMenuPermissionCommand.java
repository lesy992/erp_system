package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.authorizationservice.domain.Role;
import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class AssignMenuPermissionCommand extends SelfValidating<AssignMenuPermissionCommand> {

    @NotNull
    private final Long menuId;

    @NotNull
    private final Role role;

    @NotNull
    private final Boolean canCreate;

    @NotNull
    private final Boolean canRead;

    @NotNull
    private final Boolean canUpdate;

    @NotNull
    private final Boolean canDelete;
}
