package ru.aniskov.petproject.db.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.db.repository.QuizRepository;
import ru.aniskov.petproject.db.service.QuizService;
import ru.aniskov.petproject.db.model.Quiz;

import java.util.Optional;

@Component
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository repository;

    public QuizServiceImpl(QuizRepository repository){
        this.repository = repository;
    }

    @Override
    public Iterable<Quiz> findQuizAll() {
        return repository.findAll();
    }

    @Override
    public Quiz findQuizById(long id) {
        Optional<Quiz> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public Iterable<Quiz> findQuizByCategory(long category) {
        return repository.findQuizByCategory(category);
    }

    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return repository.save(quiz);
    }
}
