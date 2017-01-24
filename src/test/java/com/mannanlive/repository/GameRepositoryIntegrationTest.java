package com.mannanlive.repository;

import com.mannanlive.entity.ConsoleEntity;
import com.mannanlive.entity.GameEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {

    @Autowired
    private GameRepository repository;

    @Test
    public void persistUserEntityGeneratesId() {
        GameEntity expected = entityManager.persist(new GameEntity("Test Game", new ConsoleEntity(1L)));

        GameEntity actual = repository.findByNameAndConsole("Test Game", new ConsoleEntity(1L));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllGenresReturnsUniqueSortedList() {
        List<String> actual = repository.findAllGenres(new PageRequest(0, 2));
        assertThat(actual).isEqualTo(Arrays.asList("action rpg", "first person shooter"));
    }
}
