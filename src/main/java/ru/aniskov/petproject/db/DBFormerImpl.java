package ru.aniskov.petproject.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.exception.IllegalParametersException;
import ru.aniskov.petproject.pojo.SetInfo;
import ru.aniskov.petproject.pojo.model.*;
import ru.aniskov.petproject.repository.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
public class DBFormerImpl implements DBFormer{

    private QuizRepository quizRepository;

    private CategoryRepository categoryRepository;

    private SetRepository setRepository;

    private ColloquiumRepository colloquiumRepository;

    private UserRepository userRepository;

    private PassedSetLogRepository passedSetLogRepository;

    private static Logger _log = LoggerFactory.getLogger(DBFormerImpl.class);

    @Autowired
    public DBFormerImpl(QuizRepository quizRepository, CategoryRepository categoryRepository, SetRepository setRepository, ColloquiumRepository colloquiumRepository, UserRepository userRepository, PassedSetLogRepository passedSetLogRepository) {
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
        this.setRepository = setRepository;
        this.colloquiumRepository = colloquiumRepository;
        this.userRepository = userRepository;
        this.passedSetLogRepository = passedSetLogRepository;
    }

    @Override
    public Iterable<Quiz> findQuizAll() {
        return quizRepository.findAll();
    }

    @Override
    public Optional<Quiz> findQuizById(long id) {
        return quizRepository.findById(id);
    }

    @Override
    public Iterable<Quiz> findQuizByCategory(long category) {
        return quizRepository.findQuizByCategory(category);
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Optional<QuizUser> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<QuizUser> findUserAll() {
        return userRepository.findAll();
    }

    @Override
    public QuizUser saveUser(QuizUser quizUser) {
        quizUser.setRegistrerDate(new Date());
        return userRepository.save(quizUser);
    }

    @Override
    public SetInfo findSetInfo(long setId) throws IllegalParametersException {
        Optional<Set> set = setRepository.findById(setId);
        if(set.isPresent()){
            Iterable<Colloquium> colloquiums = colloquiumRepository.findBySetId(setId);
            List<Quiz> quizList = new LinkedList<>();
            for(Colloquium colloquium: colloquiums){
                Optional<Quiz> quiz = quizRepository.findById(colloquium.getQuizId());                                  //Todo: create join request instead of two select
                quiz.ifPresent(quizList::add);
            }
            return new SetInfo(set.get(), quizList);
        } else {
            throw new IllegalParametersException();
        }
    }

    @Override
    public Iterable<PassedSetLog> findPassedSetsByUserId(long userId) {
        return passedSetLogRepository.findAllByUserId(userId);
    }

    @Override
    public PassedSetLog savePassedSetLog(PassedSetLog passedSetLog) {
        passedSetLog.setDate(new Date());
        return passedSetLogRepository.save(passedSetLog);
    }
}
