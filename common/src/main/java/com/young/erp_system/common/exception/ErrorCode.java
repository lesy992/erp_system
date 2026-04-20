package com.young.erp_system.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 공통
    INVALID_INPUT_VALUE(400, "C001", "올바르지 않은 입력값입니다."),
    HANDLE_ACCESS_DENIED(403, "C002", "접근 권한이 없습니다."),

    // 인증/테넌트 (Phase 2 핵심)
    AUTH_FAILED(401, "A001", "인증에 실패했습니다."),
    TOKEN_EXPIRED(401, "A002", "토큰이 만료되었습니다."),
    TOKEN_BLACKLISTED(401, "A003", "로그아웃 처리된 토큰입니다."),
    TENANT_NOT_FOUND(404, "T001", "존재하지 않는 테넌트입니다.");

    private final int status;
    private final String code;
    private final String message;
}
