package ru.aniskov.petproject.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;
import ru.aniskov.petproject.db.service.UserService;
import ru.aniskov.petproject.pojo.model.QuizUser;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan("ru.aniskov.petproject.db")
class UserControllerTest {

    public MockitoSession session;

    @Mock
    public UserService userService;

    @Before
    public void beforeMethod() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @Test
    void test_getUserByCorrectId_thenReturnUser() {
        QuizUser expectedResult = new QuizUser();
        Mockito.when(userService.findUserById(anyLong())).thenReturn(expectedResult);
        UserController controller = new UserController(userService);

        long generatedId = Math.abs(new Random().nextLong());
        assertEquals(expectedResult, controller.getUser(generatedId));
    }

    @Test
    void test_getAllUserByUnExistedId_thenThrowException() {
        doReturn(null).when(userService).findUserById(anyLong());
        UserController controller = new UserController(userService);

        long generatedId = Math.abs(new Random().nextLong());
        Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.getUser(generatedId));
        assertEquals("404 NOT_FOUND \"User with id = " + generatedId + " not found\"", exception.getMessage());
    }

    @Test
    void postUser() {
        UserController controller = new UserController(userService);
    }

    @After
    public void afterMethod() {
        session.finishMocking();
    }
}