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
import ru.aniskov.petproject.db.service.QuizService;
import ru.aniskov.petproject.db.model.Quiz;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan("ru.aniskov.petproject.db")
class QuizControllerTest {

    public MockitoSession session;

    @Mock
    public QuizService service;

    @Before
    public void beforeMethod() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();
    }

    @Test
    void test_getQuizAll_thenReturnList() {
        List<Quiz> resultList = new LinkedList<>();
        resultList.add(new Quiz());
        resultList.add(new Quiz());
        Mockito.when(service.findQuizAll()).thenReturn(resultList);

        QuizController controller = new QuizController(service);
        assertNotNull(controller.getQuizAll());
        assertEquals(resultList.size(), StreamSupport.stream(controller.getQuizAll().spliterator(), false).count());
    }

    @Test
    void test_getQuizAll_thenReturnNull() {
        Mockito.doReturn(null).when(service).findQuizAll();

        QuizController controller = new QuizController(service);
        assertNull(controller.getQuizAll());
    }

    @Test
    void test_getQuizById_withExistedId_thenReturnQuiz() {
        Quiz result = new Quiz();
        Mockito.when(service.findQuizById(Mockito.anyLong())).thenReturn(result);

        QuizController controller = new QuizController(service);
        long generatedId = Math.abs(new Random().nextLong());
        assertNotNull(controller.getQuizById(generatedId));
        assertEquals(controller.getQuizById(generatedId), result);
    }

    @Test
    void test_getQuizById_withUnExistedId_thenReturnExc() {
        doReturn(null).when(service).findQuizById(anyLong());
        QuizController controller = new QuizController(service);

        long generatedId = Math.abs(new Random().nextLong());
        Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.getQuizById(generatedId));

        assertEquals("404 NOT_FOUND \"Quiz with id = " + generatedId + " not found\"", exception.getMessage());
    }

    @Test
    void test_getQuizByCategory_withExistedCategoryId_thenReturnQuizList() {
        long generatedCategoryId = Math.abs(new Random().nextLong());

        List<Quiz> resultList = new LinkedList<>();
        resultList.add(new Quiz(generatedCategoryId, "someQ1", "someA1"));
        resultList.add(new Quiz(generatedCategoryId, "someQ2", "someA2"));

        Mockito.when(service.findQuizByCategory(Mockito.anyLong())).thenReturn(resultList);
        QuizController controller = new QuizController(service);

        Iterable<Quiz> testResult = controller.getQuizByCategory(generatedCategoryId);

        assertNotNull(testResult);
        assertIterableEquals(resultList, testResult);
    }

    @Test
    void test_getQuizByCategory_withUnExistedCategoryId_thenReturnExc() {
        Mockito.doReturn(new LinkedList<>()).when(service).findQuizByCategory(Mockito.anyLong());
        QuizController controller = new QuizController(service);

        long generatedId = Math.abs(new Random().nextLong());
        Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.getQuizByCategory(generatedId));

        assertEquals("404 NOT_FOUND \"Quiz list with category id = " + generatedId + " not found\"", exception.getMessage());
    }

    @Test
    void test_postQuizNew_withCorrectParams_thenReturnNewQuiz() {
        Quiz quizForSave = new Quiz(1L, "Q1", "A1");
        Mockito.when(service.saveQuiz(Mockito.any())).thenReturn(quizForSave);

        QuizController controller = new QuizController(service);
        assertEquals(quizForSave, controller.postQuizNew(1L, "Q1", "A1"));
    }

    @Test
    void test_postQuizNew_withEmptyQ_thenReturnExc() {
        QuizController controller = new QuizController(service);

        Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.postQuizNew(1L, null, "A1"));

        assertEquals("400 BAD_REQUEST \"Bad parameters\"", exception.getMessage());
    }

    @Test
    void test_postQuizNew_withEmptyA_thenReturnExc() {
        QuizController controller = new QuizController(service);

        Throwable exception = assertThrows(ResponseStatusException.class, () -> controller.postQuizNew(1L, "Q1", null));

        assertEquals("400 BAD_REQUEST \"Bad parameters\"", exception.getMessage());
    }

    @After
    public void afterMethod() {
        session.finishMocking();
    }
}