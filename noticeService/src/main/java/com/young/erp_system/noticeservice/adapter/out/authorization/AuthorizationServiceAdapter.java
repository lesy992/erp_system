package com.young.erp_system.noticeservice.adapter.out.authorization;

import com.young.erp_system.common.annotation.PersistenceAdapter;
import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
import com.young.erp_system.noticeservice.application.port.out.CheckPermissionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@PersistenceAdapter
@RequiredArgsConstructor
public class AuthorizationServiceAdapter implements CheckPermissionPort {

    private final RestClient restClient;

    @Override
    public void checkPermission(String role, String resource, String action) {
        CheckAuthorizationRequest request = new CheckAuthorizationRequest(role, resource, action);

        CheckAuthorizationResponse response = restClient.post()
                .uri("/api/authorization/check")
                .header(HttpHeaders.AUTHORIZATION, resolveAuthToken())
                .body(request)
                .retrieve()
                .body(CheckAuthorizationResponse.class);

        if (response == null || !response.isAllowed()) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
    }

    private String resolveAuthToken() {
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new CustomException(ErrorCode.AUTH_FAILED);
        }
        String token = attributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        if (token == null || token.isBlank()) {
            throw new CustomException(ErrorCode.AUTH_FAILED);
        }
        return token;
    }

    record CheckAuthorizationRequest(String role, String resource, String action) {}

    record CheckAuthorizationResponse(boolean allowed, String role, String resource, String action) {}
}
