package com.young.erp_system.authorizationservice.adapter.in.web.response;

import com.young.erp_system.authorizationservice.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuResponse {

    private Long menuId;
    private String name;
    private String path;
    private String icon;
    private Long parentId;
    private Integer sortOrder;
    private boolean active;

    public static MenuResponse from(Menu menu) {
        return new MenuResponse(
                menu.getMenuId(),
                menu.getName(),
                menu.getPath(),
                menu.getIcon(),
                menu.getParentId(),
                menu.getSortOrder(),
                menu.isActive()
        );
    }
}
