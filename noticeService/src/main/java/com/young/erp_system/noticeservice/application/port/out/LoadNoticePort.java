package com.young.erp_system.noticeservice.application.port.out;

import com.young.erp_system.noticeservice.domain.Notice;

import java.util.List;
import java.util.Optional;

public interface LoadNoticePort {

    Optional<Notice> findById(Long noticeId);

    List<Notice> findAll();
}
