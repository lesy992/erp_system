package com.young.erp_system.authorizationservice.adapter.in.web.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMenuRequest {

    private String name;
    private String path;
    private String icon;
    private Long parentId;
    private Integer sortOrder;

    @NotNull
    private Boolean active;
}
