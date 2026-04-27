package com.young.erp_system.authorizationservice.application.service;

import com.young.erp_system.authorizationservice.application.port.in.AssignMenuPermissionCommand;
import com.young.erp_system.authorizationservice.application.port.in.ManageMenuPermissionCase;
import com.young.erp_system.authorizationservice.application.port.out.LoadMenuPermissionPort;
import com.young.erp_system.authorizationservice.application.port.out.LoadMenuPort;
import com.young.erp_system.authorizationservice.application.port.out.SaveMenuPermissionPort;
import com.young.erp_system.authorizationservice.domain.MenuPermission;
import com.young.erp_system.authorizationservice.domain.Role;
import com.young.erp_system.common.annotation.AuthorizationCase;
import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AuthorizationCase
@RequiredArgsConstructor
@Transactional
public class MenuPermissionManagementService implements ManageMenuPermissionCase {

    private final LoadMenuPort loadMenuPort;
    private final LoadMenuPermissionPort loadMenuPermissionPort;
    private final SaveMenuPermissionPort saveMenuPermissionPort;

    @Override
    public MenuPermission assignOrUpdateMenuPermission(AssignMenuPermissionCommand command) {
        loadMenuPort.findById(command.getMenuId())
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));

        MenuPermission menuPermission = MenuPermission.createMenuPermission(
                loadMenuPermissionPort.findByMenuIdAndRole(command.getMenuId(), command.getRole())
                        .map(MenuPermission::getId)
                        .orElse(null),
                command.getMenuId(),
                command.getRole(),
                command.getCanCreate(),
                command.getCanRead(),
                command.getCanUpdate(),
                command.getCanDelete()
        );
        return saveMenuPermissionPort.save(menuPermission);
    }

    @Override
    public void revokeMenuPermission(Long menuId, Role role) {
        loadMenuPermissionPort.findByMenuIdAndRole(menuId, role)
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_PERMISSION_NOT_FOUND));
        saveMenuPermissionPort.deleteByMenuIdAndRole(menuId, role);
    }

    @Override
    public List<MenuPermission> getMenuPermissionsByRole(Role role) {
        return loadMenuPermissionPort.findByRole(role);
    }

    @Override
    public List<MenuPermission> getMenuPermissionsByMenu(Long menuId) {
        return loadMenuPermissionPort.findByMenuId(menuId);
    }

    @Override
    public List<MenuPermission> getAccessibleMenus(Role role) {
        return loadMenuPermissionPort.findByRole(role).stream()
                .filter(MenuPermission::isCanRead)
                .collect(Collectors.toList());
    }
}
