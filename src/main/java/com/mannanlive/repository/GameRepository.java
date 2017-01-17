package com.mannanlive.repository;

import com.mannanlive.entity.Game;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GameRepository extends PagingAndSortingRepository<Game, Long> {
    Game findByNameAndConsole(String name, String Console);
}
