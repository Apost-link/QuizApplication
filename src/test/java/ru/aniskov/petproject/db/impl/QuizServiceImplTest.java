package ru.aniskov.petproject.db.impl;

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
import ru.aniskov.petproject.db.repository.QuizRepository;
import ru.aniskov.petproject.db.service.QuizService;
import ru.aniskov.petproject.db.model.Quiz;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan("ru.aniskov.petproject.db")
class QuizServiceImplTest {

    public MockitoSession session;

    @Mock
    QuizRepository repository;

    @Before
    void setUp() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @Test
    void test_findQuizAll_thenReturnList() {
        List<Quiz> resultList = new LinkedList<>();
        resultList.add(new Quiz());
        resultList.add(new Quiz());
        Mockito.when(repository.findAll()).thenReturn(resultList);

        QuizService service = new QuizServiceImpl(repository);
        assertEquals(resultList.size(), StreamSupport.stream(service.findQuizAll().spliterator(), false).count());
    }

    @Test
    void test_findQuizAll_thenReturnNull() {
        Mockito.doReturn(null).when(repository).findAll();

        QuizService service = new QuizServiceImpl(repository);
        assertNull(service.findQuizAll());
    }

    @Test
    void test_findQuizById_withExistedId_thenReturnQuiz() {
        Quiz result = new Quiz();
        Optional<Quiz> resultOpt = Optional.of(result);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(resultOpt);

        QuizService service = new QuizServiceImpl(repository);
        long generatedId = Math.abs(new Random().nextLong());
        assertNotNull(service.findQuizById(generatedId));
        assertEquals(result, service.findQuizById(generatedId));
    }

    @Test
    void test_findQuizById_withUnExistedId_thenReturnNull() {
        Mockito.doReturn(Optional.empty()).when(repository).findById(Mockito.any());

        QuizService service = new QuizServiceImpl(repository);
        long generatedId = Math.abs(new Random().nextLong());
        assertNull(service.findQuizById(generatedId));
    }

    @Test
    void findQuizByCategory() {
    }

    @Test
    void saveQuiz() {
    }

    @After
    public void afterMethod() {
        session.finishMocking();
    }
}