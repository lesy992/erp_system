package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.authorizationservice.domain.Menu;

import java.util.List;

public interface ManageMenuCase {

    Menu createMenu(CreateMenuCommand command);

    Menu updateMenu(Long menuId, UpdateMenuCommand command);

    void deleteMenu(Long menuId);

    List<Menu> getAllMenus();

    Menu getMenuById(Long menuId);
}
