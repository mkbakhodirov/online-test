package uz.pdp.service;

import uz.pdp.model.User;
import uz.pdp.model.base.Response;
import uz.pdp.service.UsersService;

public class AuthenticationService {
    public static User signIn(UsersService usersService, String username, String password) {
        return usersService.get(username, password);
    }

    public static String signUp(UsersService usersService, String username, String password) {
        boolean isSuccess = usersService.check(username);
        if (isSuccess) {
            User user = new User(username, password);
            usersService.add(user);
            return Response.SUCCESS;
        }
        else
            return Response.USERNAME_INVALID;
    }
}
