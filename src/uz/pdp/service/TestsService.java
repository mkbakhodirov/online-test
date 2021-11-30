package uz.pdp.service;

import uz.pdp.model.Subject;
import uz.pdp.model.Test;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestsService implements BaseService<Test, Subject> {
    private List<Test> tests;

    public TestsService() {
        tests = new ArrayList<>();
    }

    @Override
    public void add(Test test) {
        tests.add(test);
    }

    @Override
    public Test get(String str1, String str2) {
        return null;
    }

    @Override
    public Test getByIndex(int index) {
        return null;
    }

    @Override
    public Test get(Subject subject) {
        return null;
    }

    @Override
    public boolean check(String question) {
        for (Test test : tests) {
            if (test.getName().equals(question))
                return false;
        }
        return true;
    }

    public List<Test> getList(Subject subject) {
        List<Test> myTests = new ArrayList<>();
        UUID subjectId = subject.getId();
        for (Test test : tests) {
            if (test.getSubjectId().equals(subjectId))
                myTests.add(test);
        }
        return myTests;
    }

    @Override
    public boolean isEmpty() {
        return tests.isEmpty();
    }

    public void remove(Test test) {
        tests.remove(test);
    }
}
