package com.young.erp_system.authorizationservice.adapter.out.persistence;

import com.young.erp_system.authorizationservice.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role_permission",
        uniqueConstraints = @UniqueConstraint(columnNames = {"role", "permission_id"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "permission_id")
    private Long permissionId;

    public RolePermissionJpaEntity(Role role, Long permissionId) {
        this.role = role;
        this.permissionId = permissionId;
    }
}
