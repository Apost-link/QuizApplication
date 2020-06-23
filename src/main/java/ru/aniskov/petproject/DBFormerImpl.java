package ru.aniskov.petproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aniskov.petproject.pojo.Quiz;
import ru.aniskov.petproject.repository.CategoryRepository;
import ru.aniskov.petproject.repository.ColloquiumRepository;
import ru.aniskov.petproject.repository.QuizRepository;
import ru.aniskov.petproject.repository.SetRepository;

import java.util.Optional;

@Component
public class DBFormerImpl implements DBFormer{

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SetRepository setRepository;

    @Autowired
    private ColloquiumRepository colloquiumRepository;

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
}
