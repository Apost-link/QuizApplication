package ru.aniskov.petproject.pojo;

import lombok.Data;
import ru.aniskov.petproject.db.model.Quiz;
import ru.aniskov.petproject.db.model.QuizSet;

import java.util.Collection;

@Data
public class SetInfo {
    private QuizSet quizSet;
    private Collection<Quiz> quizList;

    public SetInfo(QuizSet quizSet, Collection<Quiz> quizList) {
        this.quizSet = quizSet;
        this.quizList = quizList;
    }
}
