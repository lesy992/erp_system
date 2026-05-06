package com.young.erp_system.noticeservice.adapter.in.web.response;

import com.young.erp_system.noticeservice.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NoticeResponse {

    private Long id;
    private String title;
    private String content;
    private String authorId;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static NoticeResponse from(Notice notice) {
        return new NoticeResponse(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getAuthorId(),
                notice.isActive(),
                notice.getCreatedAt(),
                notice.getUpdatedAt()
        );
    }
}
