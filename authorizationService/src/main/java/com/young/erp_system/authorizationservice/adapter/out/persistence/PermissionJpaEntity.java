package com.young.erp_system.authorizationservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    private String resource;

    private String action;

    private String description;

    public PermissionJpaEntity(String resource, String action, String description) {
        this.resource = resource;
        this.action = action;
        this.description = description;
    }
}
