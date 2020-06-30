package ru.aniskov.petproject.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

@Component
public class SetServiceImpl implements SetService {

    private SetRepository repository;

    private QuizService quizService;

    private ColloquiumService colloquiumService;

    @Autowired
    public SetServiceImpl(SetRepository repository){
        this.repository = repository;
    }

    @Override
    public Optional<SetInfo> findSetInfo(long setId) {
        Optional<Set> set = repository.findById(setId);
        if(set.isPresent()){
            Iterable<Colloquium> colloquiums = colloquiumService.findColloquiumBySetId(setId);
            List<Quiz> quizList = new LinkedList<>();
            for(Colloquium colloquium: colloquiums){
                Optional<Quiz> quiz = quizService.findQuizById(colloquium.getQuizId());                                  //Todo: create join request instead of two select
                quiz.ifPresent(quizList::add);
            }
            return Optional.of(new SetInfo(set.get(), quizList));
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
