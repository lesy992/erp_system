package com.young.erp_system.authorizationservice.adapter.in.web.controller;

import com.young.erp_system.authorizationservice.adapter.in.web.request.CreatePermissionRequest;
import com.young.erp_system.authorizationservice.adapter.in.web.response.PermissionResponse;
import com.young.erp_system.authorizationservice.application.port.in.CreatePermissionCommand;
import com.young.erp_system.authorizationservice.application.port.in.ManagePermissionCase;
import com.young.erp_system.authorizationservice.domain.Permission;
import com.young.erp_system.common.annotation.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("/api/authorization/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final ManagePermissionCase managePermissionCase;

    @GetMapping
    public ResponseEntity<List<PermissionResponse>> getAllPermissions() {
        List<PermissionResponse> response = managePermissionCase.getAllPermissions().stream()
                .map(PermissionResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PermissionResponse> createPermission(@RequestBody CreatePermissionRequest request) {
        CreatePermissionCommand command = CreatePermissionCommand.builder()
                .resource(request.getResource())
                .action(request.getAction())
                .description(request.getDescription())
                .build();
        Permission permission = managePermissionCase.createPermission(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(PermissionResponse.from(permission));
    }

    @DeleteMapping("/{permissionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePermission(@PathVariable Long permissionId) {
        managePermissionCase.deletePermission(permissionId);
        return ResponseEntity.noContent().build();
    }
}
