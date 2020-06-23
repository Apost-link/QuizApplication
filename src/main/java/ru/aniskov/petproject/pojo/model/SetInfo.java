package ru.aniskov.petproject.pojo.model;

import java.util.List;

public class SetInfo {
    private Set set;
    private List<Quiz> quizList;

    public SetInfo(Set set, List<Quiz> quizList) {
        this.set = set;
        this.quizList = quizList;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setColloquiumList(List<Quiz> quizList) {
        this.quizList = quizList;
    }
}
