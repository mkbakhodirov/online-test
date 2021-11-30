package uz.pdp.model.base;

public interface Response {
    String USER_NOT_FOUND = "Username or/and password is/are incorrect";
    String SUCCESS = "SUCCESS!";
    String USERNAME_INVALID = "There is already user with this username";
    String SUBJECT_INVALID = "There is already subject with this username";
    String TEST_INVALID = "There is already test with this question";
    String PAY_TYPE_INVALID = "There is already pay type with this name";
    String SWITCH = "Enter right command";
    String LIST_EMPTY = "List is empty";
    String BALANCE_INVALID = "You do not have enough money";
}
