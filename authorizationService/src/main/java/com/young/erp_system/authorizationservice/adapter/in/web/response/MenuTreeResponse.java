package com.young.erp_system.authorizationservice.adapter.in.web.response;

import com.young.erp_system.authorizationservice.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class MenuTreeResponse {

    private Long menuId;
    private String name;
    private String path;
    private String icon;
    private Integer sortOrder;
    private boolean active;
    private List<MenuTreeResponse> children;

    public static MenuTreeResponse from(Menu menu, List<MenuTreeResponse> children) {
        return new MenuTreeResponse(
                menu.getMenuId(),
                menu.getName(),
                menu.getPath(),
                menu.getIcon(),
                menu.getSortOrder(),
                menu.isActive(),
                children
        );
    }

    public static List<MenuTreeResponse> buildTree(List<Menu> menus) {
        Map<Long, List<Menu>> childrenByParent = menus.stream()
                .filter(m -> m.getParentId() != null)
                .collect(Collectors.groupingBy(Menu::getParentId));

        return menus.stream()
                .filter(m -> m.getParentId() == null)
                .sorted((a, b) -> Integer.compare(a.getSortOrder(), b.getSortOrder()))
                .map(root -> buildNode(root, childrenByParent))
                .collect(Collectors.toList());
    }

    private static MenuTreeResponse buildNode(Menu menu, Map<Long, List<Menu>> childrenByParent) {
        List<MenuTreeResponse> children = childrenByParent
                .getOrDefault(menu.getMenuId(), List.of())
                .stream()
                .sorted((a, b) -> Integer.compare(a.getSortOrder(), b.getSortOrder()))
                .map(child -> buildNode(child, childrenByParent))
                .collect(Collectors.toList());
        return from(menu, children);
    }
}
