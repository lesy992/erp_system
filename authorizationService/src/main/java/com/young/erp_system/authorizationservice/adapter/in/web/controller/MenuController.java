package com.young.erp_system.authorizationservice.adapter.in.web.controller;

import com.young.erp_system.authorizationservice.adapter.in.web.request.CreateMenuRequest;
import com.young.erp_system.authorizationservice.adapter.in.web.request.UpdateMenuRequest;
import com.young.erp_system.authorizationservice.adapter.in.web.response.MenuPermissionResponse;
import com.young.erp_system.authorizationservice.adapter.in.web.response.MenuResponse;
import com.young.erp_system.authorizationservice.adapter.in.web.response.MenuTreeResponse;
import com.young.erp_system.authorizationservice.application.port.in.CreateMenuCommand;
import com.young.erp_system.authorizationservice.application.port.in.ManageMenuCase;
import com.young.erp_system.authorizationservice.application.port.in.ManageMenuPermissionCase;
import com.young.erp_system.authorizationservice.application.port.in.UpdateMenuCommand;
import com.young.erp_system.authorizationservice.domain.Menu;
import com.young.erp_system.authorizationservice.domain.MenuPermission;
import com.young.erp_system.authorizationservice.domain.Role;
import com.young.erp_system.common.annotation.WebAdapter;
import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("/api/authorization/menus")
@RequiredArgsConstructor
public class MenuController {

    private final ManageMenuCase manageMenuCase;
    private final ManageMenuPermissionCase manageMenuPermissionCase;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MenuResponse>> getAllMenus() {
        List<MenuResponse> response = manageMenuCase.getAllMenus().stream()
                .map(MenuResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tree")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MenuTreeResponse>> getMenuTree() {
        List<Menu> menus = manageMenuCase.getAllMenus();
        return ResponseEntity.ok(MenuTreeResponse.buildTree(menus));
    }

    @GetMapping("/accessible")
    public ResponseEntity<List<MenuPermissionResponse>> getAccessibleMenus(Authentication authentication) {
        Role role = parseRole(authentication.getAuthorities().iterator().next().getAuthority());
        List<MenuPermission> permissions = manageMenuPermissionCase.getAccessibleMenus(role);

        Map<Long, Menu> menuMap = manageMenuCase.getAllMenus().stream()
                .collect(Collectors.toMap(Menu::getMenuId, Function.identity()));

        List<MenuPermissionResponse> response = permissions.stream()
                .filter(p -> menuMap.containsKey(p.getMenuId()))
                .map(p -> MenuPermissionResponse.from(p, menuMap.get(p.getMenuId())))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{menuId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuResponse> getMenuById(@PathVariable Long menuId) {
        return ResponseEntity.ok(MenuResponse.from(manageMenuCase.getMenuById(menuId)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuResponse> createMenu(@RequestBody CreateMenuRequest request) {
        CreateMenuCommand command = CreateMenuCommand.builder()
                .name(request.getName())
                .path(request.getPath())
                .icon(request.getIcon())
                .parentId(request.getParentId())
                .sortOrder(request.getSortOrder())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(MenuResponse.from(manageMenuCase.createMenu(command)));
    }

    @PutMapping("/{menuId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuResponse> updateMenu(@PathVariable Long menuId, @Valid @RequestBody UpdateMenuRequest request) {
        UpdateMenuCommand command = UpdateMenuCommand.builder()
                .name(request.getName())
                .path(request.getPath())
                .icon(request.getIcon())
                .parentId(request.getParentId())
                .sortOrder(request.getSortOrder())
                .active(request.getActive())
                .build();
        return ResponseEntity.ok(MenuResponse.from(manageMenuCase.updateMenu(menuId, command)));
    }

    @DeleteMapping("/{menuId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long menuId) {
        manageMenuCase.deleteMenu(menuId);
        return ResponseEntity.noContent().build();
    }

    private Role parseRole(String role) {
        try {
            return Role.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorCode.ROLE_NOT_FOUND);
        }
    }
}
