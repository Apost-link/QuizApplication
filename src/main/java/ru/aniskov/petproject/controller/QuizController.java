package ru.aniskov.petproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.aniskov.petproject.db.service.QuizService;
import ru.aniskov.petproject.pojo.model.Quiz;
import ru.aniskov.petproject.pojo.model.QuizUser;

import java.util.List;

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
        Quiz result = service.findQuizById(id);
        if(result != null){
            return result;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz with id = " + id + " not found");
        }
    }

    @GetMapping("/category/{category}")
    public Iterable<Quiz> getQuizByCategory(@PathVariable long category) {
        List<Quiz> result = (List<Quiz>) service.findQuizByCategory(category);
        if(!result.isEmpty()){
            return result;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz list with category id = " + category + " not found");
        }
    }

    @PostMapping("/new")
    public Quiz postQuizNew(@RequestParam(value = "category", defaultValue = "0") long category, @RequestParam(value = "question") String question, @RequestParam(value = "answer") String answer) {
        if (question == null || answer == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad parameters");
        } else {
           return service.saveQuiz(new Quiz(category, question, answer));
        }
    }
}
