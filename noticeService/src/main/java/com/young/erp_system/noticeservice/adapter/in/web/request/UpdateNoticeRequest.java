package com.young.erp_system.noticeservice.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNoticeRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;
}
