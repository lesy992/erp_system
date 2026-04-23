package com.young.erp_system.authorizationservice.application.port.out;

import com.young.erp_system.authorizationservice.domain.Permission;

import java.util.List;
import java.util.Optional;

public interface LoadPermissionPort {

    List<Permission> findAll();

    Optional<Permission> findById(Long permissionId);
}
