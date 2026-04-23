package com.young.erp_system.authorizationservice.adapter.out.persistence;

import com.young.erp_system.authorizationservice.application.port.out.LoadPermissionPort;
import com.young.erp_system.authorizationservice.application.port.out.LoadRolePermissionPort;
import com.young.erp_system.authorizationservice.application.port.out.SavePermissionPort;
import com.young.erp_system.authorizationservice.application.port.out.SaveRolePermissionPort;
import com.young.erp_system.authorizationservice.domain.Permission;
import com.young.erp_system.authorizationservice.domain.Role;
import com.young.erp_system.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class AuthorizationPersistenceAdapter implements
        LoadPermissionPort, SavePermissionPort,
        LoadRolePermissionPort, SaveRolePermissionPort {

    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Permission> findById(Long permissionId) {
        return permissionRepository.findById(permissionId).map(this::toDomain);
    }

    @Override
    public Permission save(Permission permission) {
        PermissionJpaEntity entity = new PermissionJpaEntity(
                permission.getResource(),
                permission.getAction(),
                permission.getDescription()
        );
        return toDomain(permissionRepository.save(entity));
    }

    @Override
    public void deleteById(Long permissionId) {
        permissionRepository.deleteById(permissionId);
    }

    @Override
    public List<Permission> findPermissionsByRole(Role role) {
        return rolePermissionRepository.findByRole(role).stream()
                .map(rp -> permissionRepository.findById(rp.getPermissionId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByRoleAndPermissionId(Role role, Long permissionId) {
        return rolePermissionRepository.existsByRoleAndPermissionId(role, permissionId);
    }

    @Override
    public void assignPermissionToRole(Role role, Long permissionId) {
        if (!rolePermissionRepository.existsByRoleAndPermissionId(role, permissionId)) {
            rolePermissionRepository.save(new RolePermissionJpaEntity(role, permissionId));
        }
    }

    @Override
    public void revokePermissionFromRole(Role role, Long permissionId) {
        rolePermissionRepository.deleteByRoleAndPermissionId(role, permissionId);
    }

    private Permission toDomain(PermissionJpaEntity entity) {
        return Permission.createPermission(
                new Permission.PermissionId(entity.getPermissionId()),
                new Permission.PermissionResource(entity.getResource()),
                new Permission.PermissionAction(entity.getAction()),
                new Permission.PermissionDescription(entity.getDescription())
        );
    }
}
