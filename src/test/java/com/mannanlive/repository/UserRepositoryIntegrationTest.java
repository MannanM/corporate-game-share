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
        UserEntity entity = new UserEntity("Facebook", "1234567890", "Test",
                "something@something.com", "http://somelink");
        UserEntity expected = entityManager.persist(entity);

        UserEntity actual = repository.findById(expected.getId()).get();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testGetEntity() {
        UserEntity actual = repository.findById(1L).get();

        assertThat(actual.getName()).isEqualTo("Mannan Mackie");
    }
}
