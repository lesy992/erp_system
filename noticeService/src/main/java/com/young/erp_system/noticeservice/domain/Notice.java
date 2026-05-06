package com.young.erp_system.noticeservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Notice {

    @Getter private Long id;
    @Getter private String title;
    @Getter private String content;
    @Getter private String authorId;
    @Getter private boolean active;
    @Getter private LocalDateTime createdAt;
    @Getter private LocalDateTime updatedAt;

    public static Notice createNotice(String title, String content, String authorId) {
        return new Notice(null, title, content, authorId, true, LocalDateTime.now(), LocalDateTime.now());
    }

    public static Notice of(Long id, String title, String content, String authorId,
                            boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Notice(id, title, content, authorId, active, createdAt, updatedAt);
    }

    public Notice update(String title, String content) {
        return new Notice(this.id, title, content, this.authorId, this.active, this.createdAt, LocalDateTime.now());
    }

    public Notice deactivate() {
        return new Notice(this.id, this.title, this.content, this.authorId, false, this.createdAt, LocalDateTime.now());
    }
}
