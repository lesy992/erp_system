package com.young.erp_system.authorizationservice.adapter.in.web.response;

import com.young.erp_system.authorizationservice.domain.Menu;
import com.young.erp_system.authorizationservice.domain.MenuPermission;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuPermissionResponse {

    private Long menuId;
    private String menuName;
    private String menuPath;
    private String menuIcon;
    private Long parentId;
    private Integer sortOrder;
    private String role;
    private boolean canCreate;
    private boolean canRead;
    private boolean canUpdate;
    private boolean canDelete;

    public static MenuPermissionResponse from(MenuPermission permission, Menu menu) {
        return new MenuPermissionResponse(
                menu.getMenuId(),
                menu.getName(),
                menu.getPath(),
                menu.getIcon(),
                menu.getParentId(),
                menu.getSortOrder(),
                permission.getRole().name(),
                permission.isCanCreate(),
                permission.isCanRead(),
                permission.isCanUpdate(),
                permission.isCanDelete()
        );
    }

    public static MenuPermissionResponse from(MenuPermission permission) {
        return new MenuPermissionResponse(
                permission.getMenuId(),
                null, null, null, null, null,
                permission.getRole().name(),
                permission.isCanCreate(),
                permission.isCanRead(),
                permission.isCanUpdate(),
                permission.isCanDelete()
        );
    }
}
