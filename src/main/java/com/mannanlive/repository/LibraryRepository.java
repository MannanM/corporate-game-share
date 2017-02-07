package com.mannanlive.repository;

import com.mannanlive.entity.LibraryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LibraryRepository extends PagingAndSortingRepository<LibraryEntity, Long> {
    List<LibraryEntity> findByOwnerIdOrderByCreated(long ownerId);
    LibraryEntity findByOwnerIdAndGameId(long ownerId, long gameId);
}
