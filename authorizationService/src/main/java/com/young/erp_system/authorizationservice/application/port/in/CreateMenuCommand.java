package com.young.erp_system.authorizationservice.application.port.in;

import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CreateMenuCommand extends SelfValidating<CreateMenuCommand> {

    @NotBlank private final String name;
    @NotBlank private final String path;
    private final String icon;
    private final Long parentId;
    @NotNull private final Integer sortOrder;

    @Builder
    public CreateMenuCommand(String name, String path, String icon,
                             Long parentId, Integer sortOrder) {
        this.name = name;
        this.path = path;
        this.icon = icon;
        this.parentId = parentId;
        this.sortOrder = sortOrder;
        this.validateSelf();
    }
}
