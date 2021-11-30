package uz.pdp.service;

import uz.pdp.model.History;
import uz.pdp.model.User;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HistoriesService implements BaseService<History, User> {
    private List<History> histories;

    public HistoriesService() {
        histories = new ArrayList<>();
    }

    @Override
    public void add(History history) {
        histories.add(history);
    }

    public List<History> getList(User user) {
        List<History> myHistories = new ArrayList<>();
        UUID userId = user.getId();
        for (History history : histories) {
            if (history.getUserId().equals(userId))
                myHistories.add(history);
        }
        return myHistories;
    }

    @Override
    public History get(String str1, String str2) {
        return null;
    }

    @Override
    public History getByIndex(int index) {
        return null;
    }

    @Override
    public History get(User user) {
        return null;
    }

    @Override
    public boolean check(String str1) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return histories.isEmpty();
    }
}
