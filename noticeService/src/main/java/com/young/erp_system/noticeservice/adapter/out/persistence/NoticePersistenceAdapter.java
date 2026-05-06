package com.young.erp_system.noticeservice.adapter.out.persistence;

import com.young.erp_system.common.annotation.PersistenceAdapter;
import com.young.erp_system.noticeservice.application.port.out.LoadNoticePort;
import com.young.erp_system.noticeservice.application.port.out.SaveNoticePort;
import com.young.erp_system.noticeservice.domain.Notice;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class NoticePersistenceAdapter implements LoadNoticePort, SaveNoticePort {

    private final NoticeRepository noticeRepository;

    @Override
    public Optional<Notice> findById(Long noticeId) {
        return noticeRepository.findById(noticeId).map(this::toDomain);
    }

    @Override
    public List<Notice> findAll() {
        return noticeRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Notice save(Notice notice) {
        NoticeJpaEntity entity;
        if (notice.getId() == null) {
            entity = new NoticeJpaEntity(notice.getTitle(), notice.getContent(), notice.getAuthorId(), notice.isActive());
        } else {
            entity = new NoticeJpaEntity(
                    notice.getId(), notice.getTitle(), notice.getContent(),
                    notice.getAuthorId(), notice.isActive(), notice.getCreatedAt(), notice.getUpdatedAt()
            );
        }
        return toDomain(noticeRepository.save(entity));
    }

    @Override
    public void deleteById(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }

    private Notice toDomain(NoticeJpaEntity entity) {
        return Notice.of(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthorId(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
