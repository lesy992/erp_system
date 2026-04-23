package com.young.erp_system.authorizationservice.adapter.in.web.response;

import com.young.erp_system.authorizationservice.domain.Permission;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PermissionResponse {

    private Long id;
    private String resource;
    private String action;
    private String description;

    public static PermissionResponse from(Permission permission) {
        return new PermissionResponse(
                permission.getId(),
                permission.getResource(),
                permission.getAction(),
                permission.getDescription()
        );
    }
}
