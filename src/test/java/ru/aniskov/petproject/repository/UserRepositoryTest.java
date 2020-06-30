package ru.aniskov.petproject.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aniskov.petproject.db.DBFormer;
import ru.aniskov.petproject.pojo.model.QuizUser;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.properties")
@ComponentScan("ru.aniskov.petproject.db")
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    @MockBean
    private UserRepository repository;

    @Autowired
    private DBFormer db;

    private QuizUser admin;

    @Before
    public void initEntity(){
        admin = new QuizUser("Admin", "$2a$10$KaYaTRdEuKkdy.QbQFoxC.hW6UlEB3O5nQ45ztGtZis3u2gH7yIei", "ADMIN");
        admin.setRegistrerDate(new Date());
        admin = entityManager.persist(admin);
        entityManager.flush();

        QuizUser testting = new QuizUser("asdasd", "asdasd", "USER");

        Mockito.when(repository.findByName(testting.getName())).thenReturn(Optional.of(testting));
    }

    @Test
    public void whenFindByName_thenReturnUser(){
        QuizUser result = repository.findByName(admin.getName()).get();

        Assert.assertEquals(result.getName(), admin.getName());
    }

    @Test
    public void whenFindByName_thenReturnUserTest(){
        String name = "asdasd";
        Optional<QuizUser> found = db.findUserByName(name);

        assertThat(found.get().getName())
                .isEqualTo(name);
    }

}
