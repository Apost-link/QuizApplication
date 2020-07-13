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
import ru.aniskov.petproject.pojo.model.Role;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan("ru.aniskov.petproject.controller")
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
    void test_getUserByUnExistedId_thenThrowException() {
        doReturn(null).when(userService).findUserById(anyLong());
        UserController controller = new UserController(userService);

        long generatedId = Math.abs(new Random().nextLong());
        Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.getUser(generatedId));
        assertEquals("404 NOT_FOUND \"User with id = " + generatedId + " not found\"", exception.getMessage());
    }

    @Test
    void test_postUser_withUnexistedName_thenReturnNewUserData() {
        QuizUser resultUser = new QuizUser("name", "pass",Role.ADMIN.getValue());
        Mockito.doReturn(null).when(userService).findUserByName("name");
        Mockito.doReturn(resultUser).when(userService).saveUser(Mockito.any());

        UserController controller = new UserController(userService);
        assertNotNull(controller.postUser("name", Role.ADMIN.getValue(), "pass"));
        assertEquals(resultUser, controller.postUser("name", Role.ADMIN.getValue(), "pass"));
    }

    @Test
    void test_postUser_withExistedName_thenReturnExc() {
        Mockito.doReturn(new QuizUser()).when(userService).findUserByName("name");

        UserController controller = new UserController(userService);
        Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.postUser("name", Role.ADMIN.getValue(), "pass"));
        assertEquals("400 BAD_REQUEST \"User with name name already exist\"", exception.getMessage());
    }

    @Test
    void test_postUser_withBadRole_thenReturnExc() {
        Mockito.doReturn(null).when(userService).findUserByName("name");

        UserController controller = new UserController(userService);
        Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.postUser("name", "some_str", "pass"));
        assertEquals("400 BAD_REQUEST \"Role some_str does not exist\"", exception.getMessage());
    }

    @After
    public void afterMethod() {
        session.finishMocking();
    }
}