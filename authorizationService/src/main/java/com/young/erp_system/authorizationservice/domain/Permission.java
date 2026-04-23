package com.young.erp_system.authorizationservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Permission {

    @Getter private Long id;
    @Getter private String resource;
    @Getter private String action;
    @Getter private String description;

    @Value
    public static class PermissionId {
        public PermissionId(Long value) { this.permissionId = value; }
        Long permissionId;
    }

    @Value
    public static class PermissionResource {
        public PermissionResource(String value) { this.permissionResource = value; }
        String permissionResource;
    }

    @Value
    public static class PermissionAction {
        public PermissionAction(String value) { this.permissionAction = value; }
        String permissionAction;
    }

    @Value
    public static class PermissionDescription {
        public PermissionDescription(String value) { this.permissionDescription = value; }
        String permissionDescription;
    }

    public static Permission createPermission(
            PermissionId permissionId,
            PermissionResource permissionResource,
            PermissionAction permissionAction,
            PermissionDescription permissionDescription
    ) {
        return new Permission(
                permissionId.permissionId,
                permissionResource.permissionResource,
                permissionAction.permissionAction,
                permissionDescription.permissionDescription
        );
    }
}
