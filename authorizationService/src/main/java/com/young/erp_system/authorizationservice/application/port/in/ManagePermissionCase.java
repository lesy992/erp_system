package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.authorizationservice.domain.Permission;

import java.util.List;

public interface ManagePermissionCase {

    Permission createPermission(CreatePermissionCommand command);

    void deletePermission(Long permissionId);

    List<Permission> getAllPermissions();
}
