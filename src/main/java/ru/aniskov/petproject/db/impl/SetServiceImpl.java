package ru.aniskov.petproject.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.db.repository.SetRepository;
import ru.aniskov.petproject.db.service.ColloquiumService;
import ru.aniskov.petproject.db.service.PassedSetLogService;
import ru.aniskov.petproject.db.service.QuizService;
import ru.aniskov.petproject.db.service.SetService;
import ru.aniskov.petproject.pojo.SetInfo;
import ru.aniskov.petproject.pojo.model.Colloquium;
import ru.aniskov.petproject.pojo.model.PassedSetLog;
import ru.aniskov.petproject.pojo.model.Quiz;
import ru.aniskov.petproject.pojo.model.Set;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class SetServiceImpl implements SetService {

    private SetRepository repository;

    private QuizService quizService;

    private ColloquiumService colloquiumService;

    private PassedSetLogService passedSetLogService;

    @Autowired
    public SetServiceImpl(SetRepository repository, QuizService quizService, ColloquiumService colloquiumService, PassedSetLogService passedSetLogService) {
        this.repository = repository;
        this.quizService = quizService;
        this.colloquiumService = colloquiumService;
        this.passedSetLogService = passedSetLogService;
    }

    @Override
    public SetInfo findSetInfo(long setId) {
        Optional<Set> set = repository.findById(setId);
        if(set.isPresent()){
            Iterable<Colloquium> colloquiums = colloquiumService.findColloquiumBySetId(setId);
            List<Quiz> quizList = new LinkedList<>();
            for(Colloquium colloquium: colloquiums){
                Quiz quiz = quizService.findQuizById(colloquium.getQuizId());                                  //Todo: create join request instead of two select
                if(quiz != null){
                    quizList.add(quiz);
                }
            }
            return new SetInfo(set.get(), quizList);
        } else {
            return null;
        }
    }

    @Override
    public PassedSetLog savePassedSetLog(PassedSetLog log) {
        PassedSetLog result = passedSetLogService.savePassedSetLog(log);
        if(result != null){
            return result;
        } else {
            return null;
        }
    }

    @Autowired
    public void setQuizService(QuizService quizService) {
        this.quizService = quizService;
    }

    @Autowired
    public void setColloquiumService(ColloquiumService colloquiumService) {
        this.colloquiumService = colloquiumService;
    }
}
