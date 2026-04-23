package com.young.erp_system.authorizationservice.adapter.in.web.controller;

import com.young.erp_system.authorizationservice.adapter.in.web.request.CheckAuthorizationRequest;
import com.young.erp_system.authorizationservice.adapter.in.web.response.CheckAuthorizationResponse;
import com.young.erp_system.authorizationservice.application.port.in.CheckAuthorizationCase;
import com.young.erp_system.authorizationservice.application.port.in.CheckAuthorizationCommand;
import com.young.erp_system.common.annotation.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping("/api/authorization")
@RequiredArgsConstructor
public class AuthorizationController {

    private final CheckAuthorizationCase checkAuthorizationCase;

    @PostMapping("/check")
    public ResponseEntity<CheckAuthorizationResponse> checkAuthorization(
            @RequestBody CheckAuthorizationRequest request) {

        CheckAuthorizationCommand command = CheckAuthorizationCommand.builder()
                .role(request.getRole())
                .resource(request.getResource())
                .action(request.getAction())
                .build();

        boolean allowed = checkAuthorizationCase.checkAuthorization(command);

        return ResponseEntity.ok(new CheckAuthorizationResponse(
                allowed,
                request.getRole(),
                request.getResource(),
                request.getAction()
        ));
    }
}
