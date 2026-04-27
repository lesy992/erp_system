package com.young.erp_system.authorizationservice.application.service;

import com.young.erp_system.authorizationservice.application.port.in.CreateMenuCommand;
import com.young.erp_system.authorizationservice.application.port.in.ManageMenuCase;
import com.young.erp_system.authorizationservice.application.port.in.UpdateMenuCommand;
import com.young.erp_system.authorizationservice.application.port.out.LoadMenuPort;
import com.young.erp_system.authorizationservice.application.port.out.SaveMenuPort;
import com.young.erp_system.authorizationservice.domain.Menu;
import com.young.erp_system.common.annotation.AuthorizationCase;
import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@AuthorizationCase
@RequiredArgsConstructor
@Transactional
public class MenuManagementService implements ManageMenuCase {

    private final LoadMenuPort loadMenuPort;
    private final SaveMenuPort saveMenuPort;

    @Override
    public Menu createMenu(CreateMenuCommand command) {
        Menu menu = Menu.createMenu(
                new Menu.MenuId(null),
                new Menu.MenuName(command.getName()),
                new Menu.MenuPath(command.getPath()),
                new Menu.MenuIcon(command.getIcon()),
                command.getParentId() != null ? new Menu.MenuParentId(command.getParentId()) : null,
                new Menu.MenuSortOrder(command.getSortOrder())
        );
        return saveMenuPort.save(menu);
    }

    @Override
    public Menu updateMenu(Long menuId, UpdateMenuCommand command) {
        Menu existing = loadMenuPort.findById(menuId)
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));

        Menu updated = existing.update(
                new Menu.MenuName(command.getName()),
                new Menu.MenuPath(command.getPath()),
                new Menu.MenuIcon(command.getIcon()),
                command.getParentId() != null ? new Menu.MenuParentId(command.getParentId()) : null,
                new Menu.MenuSortOrder(command.getSortOrder()),
                command.getActive()
        );
        return saveMenuPort.save(updated);
    }

    @Override
    public void deleteMenu(Long menuId) {
        loadMenuPort.findById(menuId)
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));
        saveMenuPort.deleteById(menuId);
    }

    @Override
    public List<Menu> getAllMenus() {
        return loadMenuPort.findAll();
    }

    @Override
    public Menu getMenuById(Long menuId) {
        return loadMenuPort.findById(menuId)
                .orElseThrow(() -> new CustomException(ErrorCode.MENU_NOT_FOUND));
    }
}
