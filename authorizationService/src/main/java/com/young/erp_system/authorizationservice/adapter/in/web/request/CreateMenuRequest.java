package com.young.erp_system.authorizationservice.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuRequest {

    private String name;
    private String path;
    private String icon;
    private Long parentId;
    private Integer sortOrder;
}
