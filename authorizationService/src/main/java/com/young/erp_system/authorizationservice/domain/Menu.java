package com.young.erp_system.authorizationservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Menu {

    @Getter private Long menuId;
    @Getter private String name;
    @Getter private String path;
    @Getter private String icon;
    @Getter private Long parentId;
    @Getter private Integer sortOrder;
    @Getter private boolean active;

    @Value
    public static class MenuId {
        public MenuId(Long value) { this.menuId = value; }
        Long menuId;
    }

    @Value
    public static class MenuName {
        public MenuName(String value) { this.menuName = value; }
        String menuName;
    }

    @Value
    public static class MenuPath {
        public MenuPath(String value) { this.menuPath = value; }
        String menuPath;
    }

    @Value
    public static class MenuIcon {
        public MenuIcon(String value) { this.menuIcon = value; }
        String menuIcon;
    }

    @Value
    public static class MenuParentId {
        public MenuParentId(Long value) { this.menuParentId = value; }
        Long menuParentId;
    }

    @Value
    public static class MenuSortOrder {
        public MenuSortOrder(Integer value) { this.menuSortOrder = value; }
        Integer menuSortOrder;
    }

    public static Menu createMenu(
            MenuId menuId,
            MenuName menuName,
            MenuPath menuPath,
            MenuIcon menuIcon,
            MenuParentId menuParentId,
            MenuSortOrder menuSortOrder
    ) {
        return new Menu(
                menuId.menuId,
                menuName.menuName,
                menuPath.menuPath,
                menuIcon.menuIcon,
                menuParentId != null ? menuParentId.menuParentId : null,
                menuSortOrder.menuSortOrder,
                true
        );
    }

    public Menu update(
            MenuName menuName,
            MenuPath menuPath,
            MenuIcon menuIcon,
            MenuParentId menuParentId,
            MenuSortOrder menuSortOrder,
            boolean active
    ) {
        return new Menu(
                this.menuId,
                menuName.menuName,
                menuPath.menuPath,
                menuIcon.menuIcon,
                menuParentId != null ? menuParentId.menuParentId : null,
                menuSortOrder.menuSortOrder,
                active
        );
    }

    public static Menu restore(Long menuId, String name, String path, String icon, Long parentId, Integer sortOrder, boolean active) {
        return new Menu(menuId, name, path, icon, parentId, sortOrder, active);
    }
}
