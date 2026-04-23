package com.young.erp_system.authorizationservice.adapter.in.web.controller;

import com.young.erp_system.authorizationservice.adapter.in.web.response.PermissionResponse;
import com.young.erp_system.authorizationservice.application.port.in.AssignRolePermissionCommand;
import com.young.erp_system.authorizationservice.application.port.in.ManageRolePermissionCase;
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
@RequestMapping("/api/authorization/roles")
@RequiredArgsConstructor
public class RolePermissionController {

    private final ManageRolePermissionCase manageRolePermissionCase;

    @GetMapping("/{role}/permissions")
    public ResponseEntity<List<PermissionResponse>> getPermissionsByRole(@PathVariable String role) {
        Role roleEnum = parseRole(role);
        List<PermissionResponse> response = manageRolePermissionCase.getPermissionsByRole(roleEnum).stream()
                .map(PermissionResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{role}/permissions/{permissionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> assignPermission(
            @PathVariable String role,
            @PathVariable Long permissionId) {

        AssignRolePermissionCommand command = AssignRolePermissionCommand.builder()
                .role(parseRole(role))
                .permissionId(permissionId)
                .build();
        manageRolePermissionCase.assignPermissionToRole(command);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{role}/permissions/{permissionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> revokePermission(
            @PathVariable String role,
            @PathVariable Long permissionId) {

        AssignRolePermissionCommand command = AssignRolePermissionCommand.builder()
                .role(parseRole(role))
                .permissionId(permissionId)
                .build();
        manageRolePermissionCase.revokePermissionFromRole(command);
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
