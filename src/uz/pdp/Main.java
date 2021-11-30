package uz.pdp;

import uz.pdp.model.*;
import uz.pdp.model.base.Response;
import uz.pdp.service.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // admin: username "admin" password "123";
        UsersService usersService = new UsersService();
        SubjectsService subjectsService = new SubjectsService();
        TestsService testsService = new TestsService();
        AnswersService answersService = new AnswersService();
        PayTypesService payTypesService = new PayTypesService();
        AccountsService accountsService = new AccountsService();
        HistoriesService historiesService = new HistoriesService();
        Scanner scannerInt = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1.SIGN IN\t 2.SIGN UP\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    User user = signIn(usersService);
                    if (user == null)
                        System.out.println(Response.USER_NOT_FOUND);
                    else if (user.isAdmin())
                        menuAdmin(subjectsService, testsService, answersService,payTypesService, accountsService);
                    else
                        menuUser(user, subjectsService, testsService, answersService, payTypesService, accountsService, historiesService);
                    break;
                case 2:
                    signUp(usersService);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Response.SWITCH);
            }
        }
    }

    public static User signIn(UsersService usersService) {
        Scanner scannerStr = new Scanner(System.in);
        System.out.println("Enter username");
        String username = scannerStr.next();
        System.out.println("Enter password");
        String password = scannerStr.next();
        return AuthenticationService.signIn(usersService, username, password);
    }

    public static void signUp(UsersService usersService) {
        Scanner scannerStr = new Scanner(System.in);
        System.out.println("Enter username");
        String username = scannerStr.next();
        System.out.println("Enter password");
        String password = scannerStr.next();
        String response = AuthenticationService.signUp(usersService, username, password);
        System.out.println(response);
    }

    public static void menuAdmin(SubjectsService subjectsService, TestsService testsService, AnswersService answersService, PayTypesService payTypesService, AccountsService accountsService) {
        Scanner scannerInt = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1.ADD SUBJECT\t 2.ADD TEST\t 3.UPDATE TEST\t 4.DELETE TEST\t 5.SEE BALANCE\t 6.ADD PAY TYPE\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    addSubject(subjectsService);
                    break;
                case 2:
                    addTest(subjectsService, testsService, answersService);
                    break;
                case 3:
                    updateTest(subjectsService, testsService, answersService);
                    break;
                case 4:
                    deleteTest(subjectsService, testsService);
                    break;
                case 5:
                    seeBalance(payTypesService, accountsService);
                    break;
                case 6:
                    addPayType(payTypesService, accountsService);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Response.SWITCH);
            }
        }
    }

    public static void addSubject(SubjectsService subjectsService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scannerStr.nextLine();
        boolean isSuccess = subjectsService.check(name);
        if (isSuccess) {
            System.out.println("Enter price");
            double price = scannerInt.nextDouble();
            System.out.println(Response.SUCCESS);
            Subject subject = new Subject(name, price);
            subjectsService.add(subject);
        }
        else
            System.out.println(Response.SUBJECT_INVALID);
    }

    public static void addTest(SubjectsService subjectsService, TestsService testsService, AnswersService answersService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        if (subjectsService.isEmpty()) {
            System.out.println(Response.LIST_EMPTY);
            return;
        }
        System.out.println(subjectsService.print());
        int index = scannerInt.nextInt();
        Subject subject = subjectsService.getByIndex(index - 1);
        UUID subjectId = subject.getId();
        int overallBall = 0;
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1.ADD TEST\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    System.out.println("Enter question");
                    String question = scannerStr.nextLine();
                    boolean isSuccess = testsService.check(question);
                    if (!isSuccess) {
                        System.out.println(Response.TEST_INVALID);
                        return;
                    }
                    Map<Character, String> variants = new HashMap<>();
                    int temp = 97;
                    int stepCode1 = 1;
                    while (stepCode1 != 0) {
                        System.out.println("1.ADD VARIANT\t 0.FINISH");
                        stepCode1 = scannerInt.nextInt();
                        if (stepCode1 == 1) {
                            System.out.println("Enter variant text");
                            String variant = scannerStr.nextLine();
                            variants.put((char) temp++, variant);
                        }
                    }
                    variants.forEach(
                            (k, v) -> System.out.println(k + ") " + v + ';')
                    );
                    System.out.println("Enter character of correct answer");
                    String character = scannerStr.next();
                    Answer answer = new Answer(character);
                    answersService.add(answer);
                    System.out.println("Enter ball for correct answer");
                    int ball = scannerInt.nextInt();
                    overallBall += ball;
                    Test test = new Test(question, variants, subjectId, answer.getId(), ball);
                    testsService.add(test);
                    System.out.println(Response.SUCCESS);
                    break;
                case 0:
                    subject.setBall(overallBall);
                    break;
                default:
                    System.out.println(Response.SWITCH);
            }
        }
    }

    public static void seeBalance(PayTypesService payTypesService, AccountsService accountsService) {
        Scanner scannerInt = new Scanner(System.in);
        if (!payTypesService.isEmpty()) {
            System.out.println(payTypesService);
            int index = scannerInt.nextInt();
            PayType payType = payTypesService.getByIndex(index - 1);
            Account account = accountsService.get(payType);
            double balance = account.getBalance();
            System.out.println("Balance: " + balance);
        } else
            System.out.println(Response.LIST_EMPTY);
    }

    public static void addPayType(PayTypesService payTypesService, AccountsService accountsService) {
        Scanner scannerStr = new Scanner(System.in);
        System.out.println("Enter name");
        String name = scannerStr.nextLine();
        boolean isSuccess = payTypesService.check(name);
        if (!isSuccess) {
            System.out.println(Response.PAY_TYPE_INVALID);
            return;
        }
        System.out.println(Response.SUCCESS);
        PayType payType = new PayType(name);
        payTypesService.add(payType);
        Account account = new Account(payType.getId());
        accountsService.add(account);
    }

    public static void updateTest(SubjectsService subjectsService, TestsService testsService, AnswersService answersService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        if (subjectsService.isEmpty()) {
            System.out.println(Response.LIST_EMPTY);
            return;
        }
        System.out.println(subjectsService.print());
        int index = scannerInt.nextInt();
        Subject subject = subjectsService.getByIndex(index - 1);
        List<Test> tests = testsService.getList(subject);
        if (tests.isEmpty()) {
            System.out.println(Response.LIST_EMPTY);
            return;
        }
        int temp = 1;
        for (Test test : tests) {
            System.out.print(index++ + "." + test.getName() + "\t ");
        }
        System.out.println();
        System.out.println("Choose which test to update");
        index = scannerInt.nextInt();
        Test test = testsService.getByIndex(index - 1);
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1.EDIT QUESTION\t 2.EDIT VARIANT\t 3.CHANGE CORRECT ANSWER\t 0.EXIT");
            stepCode = scannerInt.nextInt();
            switch (stepCode) {
                case 1:
                    System.out.println("Enter question text");
                    String question = scannerStr.nextLine();
                    test.setName(question);
                    break;
                case 2:
                    test.getVariants().forEach(
                            (k, v) -> System.out.println(k + ") " + v + ';')
                    );
                    System.out.println("Choose which variant to edit");
                    String character = scannerStr.next();
                    char ch = character.charAt(0);
                    if (test.getVariants().containsKey(ch)) {
                        String variant = scannerStr.nextLine();
                        test.getVariants().put(ch, variant);
                        System.out.println(Response.SUCCESS);
                    }
                    break;
                case 3:
                    test.getVariants().forEach(
                            (k, v) -> System.out.println(k + ") " + v + ';')
                    );
                    System.out.println("Enter character of correct answer");
                    character = scannerStr.next();
                    Answer answer = answersService.get(test);
                    answer.setCharacter(character);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(Response.SWITCH);
            }
        }
    }

    public static void deleteTest(SubjectsService subjectsService, TestsService testsService) {
        Scanner scannerInt = new Scanner(System.in);
        if (subjectsService.isEmpty()) {
            System.out.println(Response.LIST_EMPTY);
            return;
        }
        System.out.println(subjectsService.print());
        int index = scannerInt.nextInt();
        Subject subject = subjectsService.getByIndex(index - 1);
        List<Test> tests = testsService.getList(subject);
        if (tests.isEmpty()) {
            System.out.println(Response.LIST_EMPTY);
            return;
        }
        int temp = 1;
        for (Test test : tests) {
            System.out.print(index++ + "." + test.getName() + "\t ");
        }
        System.out.println();
        System.out.println("Choose which test to delete");
        index = scannerInt.nextInt();
        Test test = testsService.getByIndex(index - 1);
        testsService.remove(test);
        System.out.println(Response.SUCCESS);
    }

    public static void menuUser(User user, SubjectsService subjectsService, TestsService testsService, AnswersService answersService, PayTypesService payTypesService, AccountsService accountsService, HistoriesService historiesService) {
        Scanner scannerInt = new Scanner(System.in);
        int stepCode = 1;
        while (stepCode != 0) {
            System.out.println("1.SOLVE TEST\t 2.ADD BALANCE\t 3.SEE BALANCE\t 4.SEE HISTORY\t  0.EXIT"); {
                stepCode = scannerInt.nextInt();
                switch (stepCode) {
                    case 1:
                        solveTest(user, subjectsService, testsService, answersService, payTypesService, accountsService, historiesService);
                        break;
                    case 2:
                        System.out.println(payTypesService);
                        int index = scannerInt.nextInt();
                        System.out.println("Enter amount");
                        double amount = scannerInt.nextDouble();
                        double balance = user.getBalance() + amount;
                        user.setBalance(balance);
                        System.out.println(Response.SUCCESS);
                        break;
                    case 3:
                        System.out.println(user.getBalance());
                        break;
                    case 4:
                        if (historiesService.isEmpty()) {
                            System.out.println(Response.LIST_EMPTY);
                            break;
                        }
                        List<History> histories = historiesService.getList(user);
                        for (History history : histories) {
                            System.out.println(history.getSubjectName() + " - " + history.getBall() + " ball");
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println(Response.SWITCH);
                }
            }
        }
    }

    public static void solveTest(User user, SubjectsService subjectsService, TestsService testsService, AnswersService answersService, PayTypesService payTypesService, AccountsService accountsService, HistoriesService historiesService) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);
        if (subjectsService.isEmpty()) {
            System.out.println(Response.LIST_EMPTY);
            return;
        }
        System.out.println(subjectsService);
        int index = scannerInt.nextInt();
        Subject subject = subjectsService.getByIndex(index - 1);
        if (user.getBalance() - subject.getPrice() < 0) {
            System.out.println(Response.BALANCE_INVALID);
            return;
        }
        List<Test> tests = testsService.getList(subject);
        if (tests.isEmpty()) {
            System.out.println(Response.LIST_EMPTY);
            return;
        }
        if (payTypesService.isEmpty()) {
            System.out.println(Response.LIST_EMPTY);
            return;
        }
        System.out.println(payTypesService);
        index = scannerInt.nextInt();
        PayType payType = payTypesService.getByIndex(index - 1);
        Account account = accountsService.get(payType);
        Transaction.pay(user, subject.getPrice());
        Transaction.receive(account, subject.getPrice());
        int ball = 0;
        for (Test test : tests) {
            System.out.println(test.getName());
            test.getVariants().forEach(
                    (k, v) -> System.out.println(k + ") " + v + ';')
            );
            Answer answer = answersService.get(test);
            System.out.println("Enter character of answer");
            String character = scannerStr.next();
            if (answer.getCharacter().equals(character))
                ball += test.getBall();
        }
        System.out.println("Total ball: " + ball);
        History history = new History(user.getId(), subject.getName(), ball);
        historiesService.add(history);
    }
}
