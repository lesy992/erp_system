package com.young.erp_system.noticeservice.application.port.out;

import com.young.erp_system.noticeservice.domain.Notice;

public interface SaveNoticePort {

    Notice save(Notice notice);

    void deleteById(Long noticeId);
}
