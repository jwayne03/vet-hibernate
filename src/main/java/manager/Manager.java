package manager;

import exception.MyException;
import hibernate.Hibernate;
import hibernate.PersonORM;
import model.User;
import utils.Printer;
import worker.Worker;

import java.util.List;

public class Manager {

    private static Manager manager;
    private final Printer printer;
    private final Worker worker;
    private final List<User> users;
    private final Hibernate hibernate;
    private final PersonORM personORM;
    private final User user;

    private final AdminManager adminManager;

    private boolean exit;

    private Manager() {
        this.personORM = new PersonORM();
        this.hibernate = new Hibernate();
        this.users = this.personORM.selectAllPeople();
        this.printer = new Printer();
        this.worker = new Worker();
        this.adminManager = new AdminManager();
        this.exit = false;
        this.user = new User();
    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    public void execute() {
        this.printer.printLogin();
        this.login();
    }

    private void login() {
        while (!exit) {
            try {
                String user = worker.askString("Introduce your user: ");
                String password = worker.askString("Introduce your password");
                User user1 = checkUser(user, password);
                this.profile(user1.getUserType(), user1.getId(), user1);
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private User checkUser(String username, String password) throws MyException {
        for (User user : this.users)
            if (user.getName().equals(username) && user.getPassword().equals(password)) return user;
        throw new MyException(MyException.USER_PASS_INCORRECT);
    }

    private void profile(int type, int userId, User user1) {
        this.adminManager.mainmenu(this.users, this.exit, userId, type, user1);
    }
}
