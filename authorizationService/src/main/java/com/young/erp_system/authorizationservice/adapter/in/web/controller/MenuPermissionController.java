package com.young.erp_system.authorizationservice.adapter.in.web.controller;

import com.young.erp_system.authorizationservice.adapter.in.web.request.AssignMenuPermissionRequest;
import com.young.erp_system.authorizationservice.adapter.in.web.response.MenuPermissionResponse;
import com.young.erp_system.authorizationservice.application.port.in.AssignMenuPermissionCommand;
import com.young.erp_system.authorizationservice.application.port.in.ManageMenuPermissionCase;
import com.young.erp_system.authorizationservice.domain.MenuPermission;
import com.young.erp_system.authorizationservice.domain.Role;
import com.young.erp_system.common.annotation.WebAdapter;
import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("/api/authorization/menus")
@RequiredArgsConstructor
public class MenuPermissionController {

    private final ManageMenuPermissionCase manageMenuPermissionCase;

    @GetMapping("/{menuId}/permissions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MenuPermissionResponse>> getMenuPermissions(@PathVariable Long menuId) {
        List<MenuPermissionResponse> response = manageMenuPermissionCase.getMenuPermissionsByMenu(menuId).stream()
                .map(MenuPermissionResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{menuId}/permissions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MenuPermissionResponse> assignMenuPermission(
            @PathVariable Long menuId,
            @RequestBody AssignMenuPermissionRequest request) {

        AssignMenuPermissionCommand command = AssignMenuPermissionCommand.builder()
                .menuId(menuId)
                .role(parseRole(request.getRole()))
                .canCreate(request.getCanCreate())
                .canRead(request.getCanRead())
                .canUpdate(request.getCanUpdate())
                .canDelete(request.getCanDelete())
                .build();

        MenuPermission result = manageMenuPermissionCase.assignOrUpdateMenuPermission(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(MenuPermissionResponse.from(result));
    }

    @DeleteMapping("/{menuId}/permissions/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> revokeMenuPermission(@PathVariable Long menuId, @PathVariable String role) {
        manageMenuPermissionCase.revokeMenuPermission(menuId, parseRole(role));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-role/{role}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MenuPermissionResponse>> getMenuPermissionsByRole(@PathVariable String role) {
        List<MenuPermissionResponse> response = manageMenuPermissionCase.getMenuPermissionsByRole(parseRole(role)).stream()
                .map(MenuPermissionResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private Role parseRole(String role) {
        try {
            return Role.valueOf(role);
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorCode.ROLE_NOT_FOUND);
        }
    }
}
