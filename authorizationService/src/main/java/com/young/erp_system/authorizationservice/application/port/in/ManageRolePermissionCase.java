package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.authorizationservice.domain.Permission;
import com.young.erp_system.authorizationservice.domain.Role;

import java.util.List;

public interface ManageRolePermissionCase {

    void assignPermissionToRole(AssignRolePermissionCommand command);

    void revokePermissionFromRole(AssignRolePermissionCommand command);

    List<Permission> getPermissionsByRole(Role role);
}
