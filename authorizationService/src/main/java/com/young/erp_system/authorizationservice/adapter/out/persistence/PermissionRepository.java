package com.young.erp_system.authorizationservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<PermissionJpaEntity, Long> {
}
