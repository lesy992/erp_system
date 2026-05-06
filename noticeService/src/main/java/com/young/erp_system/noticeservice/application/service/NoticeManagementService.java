package com.young.erp_system.noticeservice.application.service;

import com.young.erp_system.common.annotation.NoticeCase;
import com.young.erp_system.common.exception.CustomException;
import com.young.erp_system.common.exception.ErrorCode;
import com.young.erp_system.noticeservice.application.port.in.CreateNoticeCommand;
import com.young.erp_system.noticeservice.application.port.in.ManageNoticeCase;
import com.young.erp_system.noticeservice.application.port.in.UpdateNoticeCommand;
import com.young.erp_system.noticeservice.application.port.out.CheckPermissionPort;
import com.young.erp_system.noticeservice.application.port.out.LoadNoticePort;
import com.young.erp_system.noticeservice.application.port.out.SaveNoticePort;
import com.young.erp_system.noticeservice.domain.Notice;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

@NoticeCase
@RequiredArgsConstructor
@Transactional
public class NoticeManagementService implements ManageNoticeCase {

    private static final String RESOURCE = "notice";

    private final LoadNoticePort loadNoticePort;
    private final SaveNoticePort saveNoticePort;
    private final CheckPermissionPort checkPermissionPort;

    @Override
    public Notice createNotice(CreateNoticeCommand command) {
        checkPermissionPort.checkPermission(command.getRole(), RESOURCE, "CREATE");
        Notice notice = Notice.createNotice(command.getTitle(), command.getContent(), command.getAuthorId());
        return saveNoticePort.save(notice);
    }

    @Override
    public Notice updateNotice(Long noticeId, UpdateNoticeCommand command) {
        checkPermissionPort.checkPermission(command.getRole(), RESOURCE, "UPDATE");
        Notice existing = loadNoticePort.findById(noticeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));
        return saveNoticePort.save(existing.update(command.getTitle(), command.getContent()));
    }

    @Override
    public void deleteNotice(Long noticeId, String role) {
        checkPermissionPort.checkPermission(role, RESOURCE, "DELETE");
        loadNoticePort.findById(noticeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));
        saveNoticePort.deleteById(noticeId);
    }

    @Override
    public Notice getNotice(Long noticeId, String role) {
        checkPermissionPort.checkPermission(role, RESOURCE, "READ");
        return loadNoticePort.findById(noticeId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOTICE_NOT_FOUND));
    }

    @Override
    public List<Notice> getAllNotices(String role) {
        checkPermissionPort.checkPermission(role, RESOURCE, "READ");
        return loadNoticePort.findAll();
    }
}
