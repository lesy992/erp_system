package com.young.erp_system.common.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    private String code;
    private int status;

    @Builder
    private ErrorResponse(final ErrorCode code) {
        this.message = code.getMessage();
        this.code = code.getCode();
        this.status = code.getStatus();
    }

    // 에러 발생 시 정적 팩토리 메서드로 간단히 생성
    public static ErrorResponse of(final ErrorCode code) {
        return ErrorResponse.builder()
                .code(code)
                .build();
    }
}
