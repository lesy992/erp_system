package com.young.erp_system.noticeservice.application.port.in;

import com.young.erp_system.common.utlis.SelfValidating;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class CreateNoticeCommand extends SelfValidating<CreateNoticeCommand> {

    @NotBlank private final String title;
    @NotBlank private final String content;
    @NotBlank private final String authorId;
    @NotBlank private final String role;

    @Builder
    public CreateNoticeCommand(String title, String content, String authorId, String role) {
        this.title = title;
        this.content = content;
        this.authorId = authorId;
        this.role = role;
        this.validateSelf();
    }
}
