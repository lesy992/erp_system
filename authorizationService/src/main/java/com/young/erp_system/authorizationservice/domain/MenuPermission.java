package com.young.erp_system.authorizationservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuPermission {

    @Getter private Long id;
    @Getter private Long menuId;
    @Getter private Role role;
    @Getter private boolean canCreate;
    @Getter private boolean canRead;
    @Getter private boolean canUpdate;
    @Getter private boolean canDelete;

    public static MenuPermission createMenuPermission(
            Long id,
            Long menuId,
            Role role,
            boolean canCreate,
            boolean canRead,
            boolean canUpdate,
            boolean canDelete
    ) {
        return new MenuPermission(id, menuId, role, canCreate, canRead, canUpdate, canDelete);
    }
}
