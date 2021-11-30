package uz.pdp.service;

import uz.pdp.model.Subject;
import uz.pdp.model.User;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;

public class SubjectsService implements BaseService<Subject, User> {
    private List<Subject> subjects;

    public SubjectsService() {
        subjects = new ArrayList<>();
    }

    @Override
    public void add(Subject subject) {
        subjects.add(subject);
    }

    @Override
    public Subject get(String str1, String str2) {
        return null;
    }

    @Override
    public boolean check(String name) {
        for (Subject subject : subjects) {
            if (subject.getName().equals(name))
                return false;
        }
        return true;
    }

    @Override
    public Subject getByIndex(int index) {
        return subjects.get(index);
    }

    @Override
    public String toString() {
        int temp = 1;
        StringBuilder str = new StringBuilder();
        for (Subject subject : subjects) {
            str.append(temp++);
            str.append('.');
            str.append(subject);
            str.append("\t ");
        }
        return str.toString();
    }

    @Override
    public boolean isEmpty() {
        return subjects.isEmpty();
    }

    @Override
    public Subject get(User user) {
        return null;
    }

    public String print() {
        int temp = 1;
        StringBuilder str = new StringBuilder();
        for (Subject subject : subjects) {
            str.append(temp++);
            str.append('.');
            str.append(subject.getName());
        }
        return str.toString();
    }
}
