package uz.pdp.service;

import uz.pdp.model.History;
import uz.pdp.model.User;
import uz.pdp.service.base.BaseService;

import java.util.ArrayList;
import java.util.List;

public class UsersService implements BaseService<User, History> {
    private List<User> users;

    public UsersService() {
        users = new ArrayList<>();
        User admin = new User("admin", "123", true);
        users.add(admin);
    }

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public User get(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    @Override
    public boolean check(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    @Override
    public User getByIndex(int index) {
        return null;
    }

    @Override
    public User get(History history) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return users.isEmpty();
    }
}
