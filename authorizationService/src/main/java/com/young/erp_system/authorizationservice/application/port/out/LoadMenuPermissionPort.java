package com.young.erp_system.authorizationservice.application.port.out;

import com.young.erp_system.authorizationservice.domain.MenuPermission;
import com.young.erp_system.authorizationservice.domain.Role;

import java.util.List;
import java.util.Optional;

public interface LoadMenuPermissionPort {

    List<MenuPermission> findByRole(Role role);

    List<MenuPermission> findByMenuId(Long menuId);

    Optional<MenuPermission> findByMenuIdAndRole(Long menuId, Role role);
}
