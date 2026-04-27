package com.young.erp_system.authorizationservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    private String name;

    private String path;

    private String icon;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "sort_order")
    private Integer sortOrder;

    private boolean active;

    public MenuJpaEntity(String name, String path, String icon, Long parentId, Integer sortOrder, boolean active) {
        this.name = name;
        this.path = path;
        this.icon = icon;
        this.parentId = parentId;
        this.sortOrder = sortOrder;
        this.active = active;
    }
}
