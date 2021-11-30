package uz.pdp.service;

import uz.pdp.model.Answer;
import uz.pdp.model.Test;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnswersService implements BaseService<Answer, Test> {
    private List<Answer> answers;

    public AnswersService() {
        answers = new ArrayList<>();
    }

    @Override
    public void add(Answer answer) {
        answers.add(answer);
    }

    @Override
    public Answer get(Test test) {
        UUID answerId = test.getAnswerId();
        for (Answer answer : answers) {
            if (answer.getId().equals(answerId))
                return answer;
        }
        return null;
    }

    @Override
    public Answer get(String str1, String str2) {
        return null;
    }

    @Override
    public Answer getByIndex(int index) {
        return null;
    }

    @Override
    public boolean check(String str1) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return answers.isEmpty();
    }
}
