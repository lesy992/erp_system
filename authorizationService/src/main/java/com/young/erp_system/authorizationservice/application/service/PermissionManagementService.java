package com.young.erp_system.authorizationservice.application.service;

import com.young.erp_system.authorizationservice.application.port.in.CreatePermissionCommand;
import com.young.erp_system.authorizationservice.application.port.in.ManagePermissionCase;
import com.young.erp_system.authorizationservice.application.port.out.LoadPermissionPort;
import com.young.erp_system.authorizationservice.application.port.out.SavePermissionPort;
import com.young.erp_system.authorizationservice.domain.Permission;
import com.young.erp_system.common.annotation.AuthorizationCase;
import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AuthorizationCase
@RequiredArgsConstructor
@Transactional
public class PermissionManagementService implements ManagePermissionCase {

    private final LoadPermissionPort loadPermissionPort;
    private final SavePermissionPort savePermissionPort;

    @Override
    public Permission createPermission(CreatePermissionCommand command) {
        Permission permission = Permission.createPermission(
                new Permission.PermissionId(null),
                new Permission.PermissionResource(command.getResource()),
                new Permission.PermissionAction(command.getAction()),
                new Permission.PermissionDescription(command.getDescription())
        );
        return savePermissionPort.save(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        loadPermissionPort.findById(permissionId)
                .orElseThrow(() -> new CustomException(ErrorCode.PERMISSION_NOT_FOUND));
        savePermissionPort.deleteById(permissionId);
    }

    @Override
    public List<Permission> getAllPermissions() {
        return loadPermissionPort.findAll();
    }
}
