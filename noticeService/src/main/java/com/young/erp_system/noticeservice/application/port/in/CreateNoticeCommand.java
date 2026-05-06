package com.young.erp_system.noticeservice.application.port.in;

import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CreateNoticeCommand extends SelfValidating<CreateNoticeCommand> {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

    @NotBlank
    private final String authorId;

    @NotBlank
    private final String role;
}
