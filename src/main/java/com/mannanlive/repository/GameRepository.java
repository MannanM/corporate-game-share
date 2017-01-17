package com.mannanlive.repository;

import com.mannanlive.entity.GameEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameRepository extends PagingAndSortingRepository<GameEntity, Long> {
    GameEntity findByNameAndConsole(String name, String Console);
}
