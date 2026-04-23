package com.young.erp_system.authorizationservice.application.service;

import com.young.erp_system.authorizationservice.application.port.in.AssignRolePermissionCommand;
import com.young.erp_system.authorizationservice.application.port.in.ManageRolePermissionCase;
import com.young.erp_system.authorizationservice.application.port.out.LoadPermissionPort;
import com.young.erp_system.authorizationservice.application.port.out.LoadRolePermissionPort;
import com.young.erp_system.authorizationservice.application.port.out.SaveRolePermissionPort;
import com.young.erp_system.authorizationservice.domain.Permission;
import com.young.erp_system.authorizationservice.domain.Role;
import com.young.erp_system.common.annotation.AuthorizationCase;
import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AuthorizationCase
@RequiredArgsConstructor
@Transactional
public class RolePermissionManagementService implements ManageRolePermissionCase {

    private final LoadPermissionPort loadPermissionPort;
    private final LoadRolePermissionPort loadRolePermissionPort;
    private final SaveRolePermissionPort saveRolePermissionPort;

    @Override
    public void assignPermissionToRole(AssignRolePermissionCommand command) {
        loadPermissionPort.findById(command.getPermissionId())
                .orElseThrow(() -> new CustomException(ErrorCode.PERMISSION_NOT_FOUND));
        saveRolePermissionPort.assignPermissionToRole(command.getRole(), command.getPermissionId());
    }

    @Override
    public void revokePermissionFromRole(AssignRolePermissionCommand command) {
        saveRolePermissionPort.revokePermissionFromRole(command.getRole(), command.getPermissionId());
    }

    @Override
    public List<Permission> getPermissionsByRole(Role role) {
        return loadRolePermissionPort.findPermissionsByRole(role);
    }
}
