package com.young.erp_system.authorizationservice.application.port.out;

import com.young.erp_system.authorizationservice.domain.Permission;

public interface SavePermissionPort {

    Permission save(Permission permission);

    void deleteById(Long permissionId);
}
