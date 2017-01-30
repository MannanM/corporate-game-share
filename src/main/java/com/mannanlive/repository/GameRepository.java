package com.mannanlive.repository;

import com.mannanlive.entity.ConsoleEntity;
import com.mannanlive.entity.GameEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GameRepository extends PagingAndSortingRepository<GameEntity, Long>, JpaSpecificationExecutor<GameEntity> {
    GameEntity findByNameAndConsole(String name, ConsoleEntity console);

    @Query(value = "select DISTINCT(genre) from game g join g.genres genre order by genre")
    List<String> findAllGenres(Pageable pageable);
}
