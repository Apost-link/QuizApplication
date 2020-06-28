package ru.aniskov.petproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.aniskov.petproject.db.DBFormer;
import ru.aniskov.petproject.exception.IllegalParametersException;
import ru.aniskov.petproject.pojo.model.Quiz;
import ru.aniskov.petproject.pojo.SetInfo;

import java.util.Optional;

@RestController()
@RequestMapping(path = "${v1API}/quiz")
public class QuizController {

    private static Logger _log = LoggerFactory.getLogger(QuizController.class);

    private DBFormer db;

    @Autowired
    public QuizController(DBFormer dbFormer) {
        this.db = dbFormer;
    }

    @GetMapping("/all")
    public Iterable<Quiz> getQuizAll() {
        return db.findQuizAll();
    }

    @GetMapping("/{id}")
    public Optional<Quiz> getQuizById(@PathVariable long id){
        return db.findQuizById(id);
    }

    @GetMapping("/category/{category}")
    public Iterable<Quiz> getQuizByCategory(@PathVariable long category){
        return db.findQuizByCategory(category);
    }

    @GetMapping("/set/{id}")
    public SetInfo getSetInfoById(@PathVariable long id){
        try{
            return db.findSetInfo(id);
        }catch (IllegalParametersException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad parameters", e);
        }
    }

    @PostMapping("/new")
    public Quiz postQuizNew(@RequestParam(value = "category", defaultValue = "0") Integer category, @RequestParam(value = "question") String question, @RequestParam(value = "answer") String answer) {
        Quiz result;
        try {
            if(question == null || answer == null){
                throw new IllegalParametersException();
            }else{
                result = db.saveQuiz(new Quiz(category, question, answer));
            }
        } catch (IllegalParametersException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bad parameters", e);
        }
        return result;
    }
}
