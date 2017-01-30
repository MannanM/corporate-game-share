package com.mannanlive.repository;

import com.mannanlive.builder.SpecificationBuilder;
import com.mannanlive.entity.ConsoleEntity;
import com.mannanlive.entity.GameEntity;
import com.mannanlive.model.SearchCriterion;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class GameRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {

    @Autowired
    private GameRepository repository;


    private SpecificationBuilder<GameEntity> specificationBuilder = new SpecificationBuilder<>();

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

    @Test
    public void findAllByNameSpecificationReturnsAsExpected() {
        SearchCriterion searchCriterion =
                new SearchCriterion("name", null, "like", "destiny");
        List<SearchCriterion> searchCriteria = asList(searchCriterion);
        Specification<GameEntity> specification = specificationBuilder.createSpecification(searchCriteria);

        List<GameEntity> actual = repository.findAll(specification);
        assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    public void findAllByGenreSpecificationReturnsAsExpected() {
        SearchCriterion searchCriterion =
                new SearchCriterion("genres", null, "contains", "action rpg");
        List<SearchCriterion> searchCriteria = asList(searchCriterion);
        Specification<GameEntity> specification = specificationBuilder.createSpecification(searchCriteria);

        List<GameEntity> actual = repository.findAll(specification);
        assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    public void findAllIsFilteredBySpecificationAsExpected() {
        ConsoleEntity consoleEntity = new ConsoleEntity(2L);
        consoleEntity.setShortName("X1");
        GameEntity gameEntityToFilter = new GameEntity("Xbox Game", consoleEntity);

        entityManager.persist(gameEntityToFilter);

        SearchCriterion searchCriterion =
                new SearchCriterion("console", "shortName", "join", "PS4");
        List<SearchCriterion> searchCriteria = asList(searchCriterion);
        Specification<GameEntity> specification = specificationBuilder.createSpecification(searchCriteria);

        List<GameEntity> actual = repository.findAll(specification);
        assertThat(actual.size()).isEqualTo(1);
    }



}
