package ru.aniskov.petproject.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aniskov.petproject.pojo.model.QuizUser;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    private QuizUser admin;

    @Before
    public void initEntity(){
        admin = new QuizUser("Admin", "$2a$10$KaYaTRdEuKkdy.QbQFoxC.hW6UlEB3O5nQ45ztGtZis3u2gH7yIei", "ADMIN");
        admin.setRegistrerDate(new Date());
        admin = entityManager.persist(admin);
        entityManager.flush();
    }

    @Test
    public void whenFindByName_thenReturnUser(){
        QuizUser result = repository.findByName(admin.getName());

        Assert.assertEquals(result.getName(), admin.getName());
    }

}
