package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.authorizationservice.domain.MenuPermission;
import com.young.erp_system.authorizationservice.domain.Role;

import java.util.List;

public interface ManageMenuPermissionCase {

    MenuPermission assignOrUpdateMenuPermission(AssignMenuPermissionCommand command);

    void revokeMenuPermission(Long menuId, Role role);

    List<MenuPermission> getMenuPermissionsByRole(Role role);

    List<MenuPermission> getMenuPermissionsByMenu(Long menuId);

    List<MenuPermission> getAccessibleMenus(Role role);
}
