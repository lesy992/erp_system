package com.young.erp_system.noticeservice.application.port.in;

import com.young.erp_system.noticeservice.domain.Notice;

import java.util.List;

public interface ManageNoticeCase {

    Notice createNotice(CreateNoticeCommand command);

    Notice updateNotice(Long noticeId, UpdateNoticeCommand command);

    void deleteNotice(Long noticeId, String role);

    Notice getNotice(Long noticeId, String role);

    List<Notice> getAllNotices(String role);
}
