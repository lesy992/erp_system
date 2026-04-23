package com.young.erp_system.authorizationservice.application.port.out;

import com.young.erp_system.authorizationservice.domain.Permission;
import com.young.erp_system.authorizationservice.domain.Role;

import java.util.List;

public interface LoadRolePermissionPort {

    List<Permission> findPermissionsByRole(Role role);

    boolean existsByRoleAndPermissionId(Role role, Long permissionId);
}
