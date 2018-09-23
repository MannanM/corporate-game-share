package com.mannanlive.repository;

import com.mannanlive.entity.ConsoleEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {

    @Autowired
    private ConsoleRepository repository;

    @Test
    public void persistUserEntityGeneratesId() {
        ConsoleEntity expected = entityManager.persist(new ConsoleEntity());

        ConsoleEntity actual = repository.findById(expected.getId()).get();

        assertThat(actual).isEqualTo(expected);
    }
}
