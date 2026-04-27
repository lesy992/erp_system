package com.young.erp_system.authorizationservice.adapter.out.persistence;

import com.young.erp_system.authorizationservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MenuPermissionRepository extends JpaRepository<MenuPermissionJpaEntity, Long> {

    List<MenuPermissionJpaEntity> findByRole(Role role);

    List<MenuPermissionJpaEntity> findByMenuId(Long menuId);

    Optional<MenuPermissionJpaEntity> findByMenuIdAndRole(Long menuId, Role role);

    @Transactional
    void deleteByMenuIdAndRole(Long menuId, Role role);
}
