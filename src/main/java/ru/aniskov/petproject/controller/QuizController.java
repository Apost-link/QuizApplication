package ru.aniskov.petproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.aniskov.petproject.db.DBFormer;
import ru.aniskov.petproject.exception.IllegalParametersException;
import ru.aniskov.petproject.configuration.TestConfig;
import ru.aniskov.petproject.pojo.Quiz;

import java.util.Optional;

@RestController()
public class QuizController {

    private static Logger _log = LoggerFactory.getLogger(QuizController.class);

    @Autowired
    private DBFormer db;

    public QuizController() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
    }

    @GetMapping("/quiz/all")
    public Iterable<Quiz> getQuizAll() {
        return db.findQuizAll();
    }

    @GetMapping("/quiz/{id}")
    public Optional<Quiz> getQuizById(@PathVariable long id){
        return db.findQuizById(id);
    }

    @GetMapping("/quiz/category/{category}")
    public Iterable<Quiz> getQuizByCategory(@PathVariable long category){
        return db.findQuizByCategory(category);
    }


    @PostMapping("/quiz/new")
    public Quiz postQuizNew(@RequestParam(value = "category", defaultValue = "0") Integer category, @RequestParam(value = "question") String question, @RequestParam(value = "answer") String answer) {
        Quiz result;
        try {
            if(question == null || answer == null){
                throw new IllegalParametersException();
            }else{
                result = db.saveQuiz(new Quiz(category, question, answer));
            }
        } catch (IllegalParametersException exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad parameters", exc);
        }
        return result;
    }

}
