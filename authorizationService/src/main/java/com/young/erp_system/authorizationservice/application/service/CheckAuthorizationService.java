package com.young.erp_system.authorizationservice.application.service;

import com.young.erp_system.authorizationservice.application.port.in.CheckAuthorizationCase;
import com.young.erp_system.authorizationservice.application.port.in.CheckAuthorizationCommand;
import com.young.erp_system.authorizationservice.application.port.out.LoadRolePermissionPort;
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
public class CheckAuthorizationService implements CheckAuthorizationCase {

    private final LoadRolePermissionPort loadRolePermissionPort;

    @Override
    public boolean checkAuthorization(CheckAuthorizationCommand command) {
        Role role;
        try {
            role = Role.valueOf(command.getRole());
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorCode.ROLE_NOT_FOUND);
        }

        List<Permission> permissions = loadRolePermissionPort.findPermissionsByRole(role);

        return permissions.stream().anyMatch(p ->
                p.getResource().equalsIgnoreCase(command.getResource()) &&
                p.getAction().equalsIgnoreCase(command.getAction())
        );
    }
}
