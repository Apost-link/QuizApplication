package ru.aniskov.petproject.db.impl;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
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
import ru.aniskov.petproject.db.repository.UserRepository;
import ru.aniskov.petproject.db.service.UserService;
import ru.aniskov.petproject.db.model.QuizUser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan("ru.aniskov.petproject.db")
class UserServiceImplTest{

    public MockitoSession session;

    @Mock
    UserRepository userRepository;

    @Before
    public void beforeMethod() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @Test
    public void test_findUserById_withExistingId_thenReturnUser(){
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(new QuizUser()));
        UserService service = new UserServiceImpl(userRepository);

        long generatedId = new Random().nextLong();
        assertNotNull(service.findUserById(generatedId));
    }

    @Test
    public void test_findUserById_withNotExistingId_thenReturnEmptyUser(){
        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        UserService service = new UserServiceImpl(userRepository);

        long generatedId = new Random().nextLong();
        assertNull(service.findUserById(generatedId));
    }

    @Test
    public void test_findUserByName_withExistingName_thenReturnUser(){
        Mockito.when(userRepository.findByName(Mockito.any())).thenReturn(Optional.of(new QuizUser()));
        UserService service = new UserServiceImpl(userRepository);

        String generatedStr = RandomString.make(12);
        assertNotNull(service.findUserByName(generatedStr));
    }

    @Test
    public void test_findUserByName_withNotExistingName_thenReturnEmptyUser(){
        Mockito.when(userRepository.findByName(Mockito.any())).thenReturn(Optional.empty());
        UserService service = new UserServiceImpl(userRepository);

        String generatedStr = RandomString.make(12);
        assertNull(service.findUserByName(generatedStr));
    }

    @Test
    public void test_findUserAll_thenReturnList(){
        List<QuizUser> resultList = new LinkedList<>();
        resultList.add(new QuizUser());
        resultList.add(new QuizUser());
        Mockito.when(userRepository.findAll()).thenReturn(resultList);
        UserService service = new UserServiceImpl(userRepository);

        assertNotNull(service.findUserAll());
        assertEquals(resultList.size(), StreamSupport.stream(service.findUserAll().spliterator(), false).count());
    }

    @Test
    public void test_findUserAll_thenReturnNull(){
        Mockito.when(userRepository.findAll()).thenReturn(new LinkedList<QuizUser>());
        UserService service = new UserServiceImpl(userRepository);

        assertNull(service.findUserAll());
    }

    @After
    public void afterMethod() {
        session.finishMocking();
    }
}