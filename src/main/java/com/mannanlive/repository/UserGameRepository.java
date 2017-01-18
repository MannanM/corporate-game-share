package com.mannanlive.repository;

import com.mannanlive.entity.UserGameEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserGameRepository extends PagingAndSortingRepository<UserGameEntity, Long> {

    List<UserGameEntity> findByUserIdOrderByDateAdded(long id);
}
