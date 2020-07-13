package ru.aniskov.petproject.db.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aniskov.petproject.db.repository.SetRepository;
import ru.aniskov.petproject.db.service.ColloquiumService;
import ru.aniskov.petproject.db.service.QuizService;
import ru.aniskov.petproject.db.service.SetService;
import ru.aniskov.petproject.pojo.SetInfo;
import ru.aniskov.petproject.pojo.model.Colloquium;
import ru.aniskov.petproject.pojo.model.Quiz;
import ru.aniskov.petproject.pojo.model.Set;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
@ComponentScan("ru.aniskov.petproject.db")
class SetServiceImplTest {

    MockitoSession session;

    @Mock
    private SetRepository setRepository;

    @Mock
    private ColloquiumService colloquiumService;

    @Mock
    private QuizService quizService;

    @Before
    public void beforeMethod() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();

    }

    @Test
    void test_findSetInfo_withExistedSet_ThenReturnSetInfo() {
        List<Quiz> resultQuizList = new LinkedList<>();
        resultQuizList.add(new Quiz());
        resultQuizList.add(new Quiz());
        Optional<SetInfo> result = Optional.of(new SetInfo(new Set("NewSetName"), resultQuizList));
        List<Colloquium> colloquiums = new LinkedList<>();
        colloquiums.add(new Colloquium(1L,1L));
        colloquiums.add(new Colloquium(2L,2L));
        Mockito.when(setRepository.findById(1L)).thenReturn(Optional.of(new Set("NewSetName")));
        Mockito.when(colloquiumService.findColloquiumBySetId(1L)).thenReturn(colloquiums);
        Mockito.when(quizService.findQuizById(1L)).thenReturn(new Quiz());
        Mockito.when(quizService.findQuizById(2L)).thenReturn(new Quiz());

        SetService service = new SetServiceImpl(setRepository, quizService, colloquiumService);

        Assert.assertEquals(result.get().getQuizList().size(), service.findSetInfo(1L).get().getQuizList().size());
        Assert.assertEquals(result.get().getSet().getSetName(), service.findSetInfo(1L).get().getSet().getSetName());
    }

    @Test
    void findUnExistedSetThenReturnNull() {
        Mockito.when(setRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        long generatedId = new Random().nextLong();

        SetService service = new SetServiceImpl(setRepository, null, null);

        Assert.assertNull(service.findSetInfo(generatedId));
    }

    @After
    public void afterMethod() {
        session.finishMocking();
    }
}