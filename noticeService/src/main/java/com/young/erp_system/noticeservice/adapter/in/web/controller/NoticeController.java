package com.young.erp_system.noticeservice.adapter.in.web.controller;

import com.young.erp_system.common.annotation.WebAdapter;
import com.young.erp_system.noticeservice.adapter.in.web.request.CreateNoticeRequest;
import com.young.erp_system.noticeservice.adapter.in.web.request.UpdateNoticeRequest;
import com.young.erp_system.noticeservice.adapter.in.web.response.NoticeResponse;
import com.young.erp_system.noticeservice.application.port.in.CreateNoticeCommand;
import com.young.erp_system.noticeservice.application.port.in.ManageNoticeCase;
import com.young.erp_system.noticeservice.application.port.in.UpdateNoticeCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {

    private final ManageNoticeCase manageNoticeCase;

    @GetMapping
    public ResponseEntity<List<NoticeResponse>> getAllNotices(Authentication authentication) {
        String role = extractRole(authentication);
        List<NoticeResponse> response = manageNoticeCase.getAllNotices(role).stream()
                .map(NoticeResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse> getNotice(@PathVariable Long noticeId, Authentication authentication) {
        String role = extractRole(authentication);
        return ResponseEntity.ok(NoticeResponse.from(manageNoticeCase.getNotice(noticeId, role)));
    }

    @PostMapping
    public ResponseEntity<NoticeResponse> createNotice(
            @RequestBody @Valid CreateNoticeRequest request,
            Authentication authentication) {

        String role = extractRole(authentication);
        CreateNoticeCommand command = CreateNoticeCommand.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .authorId(authentication.getName())
                .role(role)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(NoticeResponse.from(manageNoticeCase.createNotice(command)));
    }

    @PutMapping("/{noticeId}")
    public ResponseEntity<NoticeResponse> updateNotice(
            @PathVariable Long noticeId,
            @RequestBody @Valid UpdateNoticeRequest request,
            Authentication authentication) {

        String role = extractRole(authentication);
        UpdateNoticeCommand command = UpdateNoticeCommand.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .role(role)
                .build();
        return ResponseEntity.ok(NoticeResponse.from(manageNoticeCase.updateNotice(noticeId, command)));
    }

    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long noticeId, Authentication authentication) {
        manageNoticeCase.deleteNotice(noticeId, extractRole(authentication));
        return ResponseEntity.noContent().build();
    }

    private String extractRole(Authentication authentication) {
        return authentication.getAuthorities().iterator().next().getAuthority();
    }
}
