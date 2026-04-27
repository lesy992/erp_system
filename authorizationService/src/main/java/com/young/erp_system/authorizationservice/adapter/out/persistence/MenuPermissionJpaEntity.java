package com.young.erp_system.authorizationservice.adapter.out.persistence;

import com.young.erp_system.authorizationservice.domain.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_permission",
        uniqueConstraints = @UniqueConstraint(columnNames = {"menu_id", "role"}))
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuPermissionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menu_id")
    private Long menuId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "can_create")
    private boolean canCreate;

    @Column(name = "can_read")
    private boolean canRead;

    @Column(name = "can_update")
    private boolean canUpdate;

    @Column(name = "can_delete")
    private boolean canDelete;

    public MenuPermissionJpaEntity(Long menuId, Role role, boolean canCreate, boolean canRead, boolean canUpdate, boolean canDelete) {
        this.menuId = menuId;
        this.role = role;
        this.canCreate = canCreate;
        this.canRead = canRead;
        this.canUpdate = canUpdate;
        this.canDelete = canDelete;
    }
}
