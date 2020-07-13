package ru.aniskov.petproject.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.aniskov.petproject.db.service.QuizService;
import ru.aniskov.petproject.pojo.model.Quiz;

import java.util.Optional;

@RestController()
@RequestMapping(path = "${v1API}/quiz")
public class QuizController {

    private static Logger _log = LoggerFactory.getLogger(QuizController.class);

    private QuizService service;

    @Autowired
    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public Iterable<Quiz> getQuizAll() {
        return service.findQuizAll();
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable long id) {
        return service.findQuizById(id);
    }

    @GetMapping("/category/{category}")
    public Iterable<Quiz> getQuizByCategory(@PathVariable long category) {
        return service.findQuizByCategory(category);
    }

    @PostMapping("/new")
    public Quiz postQuizNew(@RequestParam(value = "category", defaultValue = "0") Integer category, @RequestParam(value = "question") String question, @RequestParam(value = "answer") String answer) {
        Quiz result;
        if (question == null || answer == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad parameters");
        } else {
            result = service.saveQuiz(new Quiz(category, question, answer));
        }
        return result;
    }
}
