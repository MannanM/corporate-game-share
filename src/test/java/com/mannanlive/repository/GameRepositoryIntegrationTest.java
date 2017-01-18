package com.mannanlive.repository;

import com.mannanlive.entity.GameEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {

    @Autowired
    private GameRepository repository;

    @Test
    public void persistUserEntityGeneratesId() {
        GameEntity expected = entityManager.persist(new GameEntity("Test Game", "Test Console"));

        GameEntity actual = repository.findByNameAndConsole("Test Game", "Test Console");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllGenresReturnsUniqueSortedList() {
        GameEntity entity = new GameEntity("Test Game", "Test Console");
        entity.setGenres(Arrays.asList("b", "a"));
        entityManager.persist(entity);
        entity = new GameEntity("Test Game2", "Test Console2");
        entity.setGenres(Arrays.asList("c", "b"));
        entityManager.persist(entity);

        List<String> actual = repository.findAllGenres(new PageRequest(0, 2));
        assertThat(actual).isEqualTo(Arrays.asList("a", "b"));

        actual = repository.findAllGenres(new PageRequest(1, 2));
        assertThat(actual).isEqualTo(Collections.singletonList("c"));
    }
}
