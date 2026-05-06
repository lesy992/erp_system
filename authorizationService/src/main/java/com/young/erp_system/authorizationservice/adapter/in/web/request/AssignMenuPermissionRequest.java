package com.young.erp_system.authorizationservice.adapter.in.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignMenuPermissionRequest {

    private String role;

    @NotNull
    private Boolean canCreate;

    @NotNull
    private Boolean canRead;

    @NotNull
    private Boolean canUpdate;

    @NotNull
    private Boolean canDelete;
}
