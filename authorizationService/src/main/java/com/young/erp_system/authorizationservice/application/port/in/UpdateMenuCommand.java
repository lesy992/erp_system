package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UpdateMenuCommand extends SelfValidating<UpdateMenuCommand> {

    @NotBlank
    private final String name;

    @NotBlank
    private final String path;

    private final String icon;

    private final Long parentId;

    @NotNull
    private final Integer sortOrder;

    @NotNull
    private final Boolean active;
}
