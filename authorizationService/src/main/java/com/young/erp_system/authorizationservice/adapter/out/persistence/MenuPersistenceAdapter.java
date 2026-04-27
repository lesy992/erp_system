package com.young.erp_system.authorizationservice.adapter.out.persistence;

import com.young.erp_system.authorizationservice.application.port.out.LoadMenuPermissionPort;
import com.young.erp_system.authorizationservice.application.port.out.LoadMenuPort;
import com.young.erp_system.authorizationservice.application.port.out.SaveMenuPermissionPort;
import com.young.erp_system.authorizationservice.application.port.out.SaveMenuPort;
import com.young.erp_system.authorizationservice.domain.Menu;
import com.young.erp_system.authorizationservice.domain.MenuPermission;
import com.young.erp_system.authorizationservice.domain.Role;
import com.young.erp_system.common.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class MenuPersistenceAdapter implements
        LoadMenuPort, SaveMenuPort,
        LoadMenuPermissionPort, SaveMenuPermissionPort {

    private final MenuRepository menuRepository;
    private final MenuPermissionRepository menuPermissionRepository;

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Menu> findById(Long menuId) {
        return menuRepository.findById(menuId).map(this::toDomain);
    }

    @Override
    public Menu save(Menu menu) {
        MenuJpaEntity entity;
        if (menu.getMenuId() != null) {
            entity = menuRepository.findById(menu.getMenuId())
                    .orElse(new MenuJpaEntity());
            entity.setName(menu.getName());
            entity.setPath(menu.getPath());
            entity.setIcon(menu.getIcon());
            entity.setParentId(menu.getParentId());
            entity.setSortOrder(menu.getSortOrder());
            entity.setActive(menu.isActive());
        } else {
            entity = new MenuJpaEntity(
                    menu.getName(),
                    menu.getPath(),
                    menu.getIcon(),
                    menu.getParentId(),
                    menu.getSortOrder(),
                    menu.isActive()
            );
        }
        return toDomain(menuRepository.save(entity));
    }

    @Override
    public void deleteById(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    @Override
    public List<MenuPermission> findByRole(Role role) {
        return menuPermissionRepository.findByRole(role).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MenuPermission> findByMenuId(Long menuId) {
        return menuPermissionRepository.findByMenuId(menuId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MenuPermission> findByMenuIdAndRole(Long menuId, Role role) {
        return menuPermissionRepository.findByMenuIdAndRole(menuId, role).map(this::toDomain);
    }

    @Override
    public MenuPermission save(MenuPermission menuPermission) {
        MenuPermissionJpaEntity entity;
        if (menuPermission.getId() != null) {
            entity = menuPermissionRepository.findById(menuPermission.getId())
                    .orElse(new MenuPermissionJpaEntity());
            entity.setMenuId(menuPermission.getMenuId());
            entity.setRole(menuPermission.getRole());
            entity.setCanCreate(menuPermission.isCanCreate());
            entity.setCanRead(menuPermission.isCanRead());
            entity.setCanUpdate(menuPermission.isCanUpdate());
            entity.setCanDelete(menuPermission.isCanDelete());
        } else {
            entity = new MenuPermissionJpaEntity(
                    menuPermission.getMenuId(),
                    menuPermission.getRole(),
                    menuPermission.isCanCreate(),
                    menuPermission.isCanRead(),
                    menuPermission.isCanUpdate(),
                    menuPermission.isCanDelete()
            );
        }
        return toDomain(menuPermissionRepository.save(entity));
    }

    @Override
    public void deleteByMenuIdAndRole(Long menuId, Role role) {
        menuPermissionRepository.deleteByMenuIdAndRole(menuId, role);
    }

    private Menu toDomain(MenuJpaEntity entity) {
        return Menu.restore(
                entity.getMenuId(),
                entity.getName(),
                entity.getPath(),
                entity.getIcon(),
                entity.getParentId(),
                entity.getSortOrder(),
                entity.isActive()
        );
    }

    private MenuPermission toDomain(MenuPermissionJpaEntity entity) {
        return MenuPermission.createMenuPermission(
                entity.getId(),
                entity.getMenuId(),
                entity.getRole(),
                entity.isCanCreate(),
                entity.isCanRead(),
                entity.isCanUpdate(),
                entity.isCanDelete()
        );
    }
}
