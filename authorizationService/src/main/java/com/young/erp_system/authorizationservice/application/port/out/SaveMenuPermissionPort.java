package com.young.erp_system.authorizationservice.application.port.out;

import com.young.erp_system.authorizationservice.domain.MenuPermission;
import com.young.erp_system.authorizationservice.domain.Role;

public interface SaveMenuPermissionPort {

    MenuPermission save(MenuPermission menuPermission);

    void deleteByMenuIdAndRole(Long menuId, Role role);
}
