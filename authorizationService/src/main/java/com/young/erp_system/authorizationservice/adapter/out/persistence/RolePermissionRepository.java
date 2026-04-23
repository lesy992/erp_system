package com.young.erp_system.authorizationservice.adapter.out.persistence;

import com.young.erp_system.authorizationservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermissionJpaEntity, Long> {

    List<RolePermissionJpaEntity> findByRole(Role role);

    boolean existsByRoleAndPermissionId(Role role, Long permissionId);

    @Transactional
    void deleteByRoleAndPermissionId(Role role, Long permissionId);
}
