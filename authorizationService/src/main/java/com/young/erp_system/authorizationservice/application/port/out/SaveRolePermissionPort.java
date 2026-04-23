package com.young.erp_system.authorizationservice.application.port.out;

import com.young.erp_system.authorizationservice.domain.Role;

public interface SaveRolePermissionPort {

    void assignPermissionToRole(Role role, Long permissionId);

    void revokePermissionFromRole(Role role, Long permissionId);
}
