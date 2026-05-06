package com.young.erp_system.noticeservice.application.port.in;

import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class UpdateNoticeCommand extends SelfValidating<UpdateNoticeCommand> {

    @NotBlank private final String title;
    @NotBlank private final String content;
    @NotBlank private final String role;

    @Builder
    public UpdateNoticeCommand(String title, String content, String role) {
        this.title = title;
        this.content = content;
        this.role = role;
        this.validateSelf();
    }
}
