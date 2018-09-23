package com.mannanlive.repository;

import com.mannanlive.entity.UserEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryIntegrationTest extends AbstractRepositoryIntegrationTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void persistUserEntityGeneratesId() {
        UserEntity entity = new UserEntity("something@something.com", "Test", "hahah", "@something.com");
        UserEntity expected = entityManager.persist(entity);

        UserEntity actual = repository.findById(expected.getId()).get();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetEntity() {
        UserEntity actual = repository.findById(1L).get();

        assertThat(actual.getLogin()).isEqualTo("tom@ex.com.au");
    }
}
