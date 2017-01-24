package com.mannanlive.repository;

import com.mannanlive.entity.LibraryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LibraryRepository extends PagingAndSortingRepository<LibraryEntity, Long> {
    List<LibraryEntity> findByUserIdOrderByAdded(long id);
}
