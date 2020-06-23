package ru.aniskov.petproject.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.pojo.Quiz;
import ru.aniskov.petproject.pojo.QuizUser;
import ru.aniskov.petproject.repository.*;

import java.util.Optional;

@Component
public class DBFormerImpl implements DBFormer{

    private QuizRepository quizRepository;

    private CategoryRepository categoryRepository;

    private SetRepository setRepository;

    private ColloquiumRepository colloquiumRepository;

    private UserRepository userRepository;

    @Autowired
    public DBFormerImpl(QuizRepository quizRepository, CategoryRepository categoryRepository, SetRepository setRepository, ColloquiumRepository colloquiumRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
        this.setRepository = setRepository;
        this.colloquiumRepository = colloquiumRepository;
        this.userRepository = userRepository;
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
        return userRepository.save(quizUser);
    }
}
