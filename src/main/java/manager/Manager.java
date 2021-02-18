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

    private AdminManager adminManager;
    private AuxiliarManager auxiliarManager;
    private VetManager vetManager;

    private Manager() {
        this.personORM = new PersonORM();
        this.hibernate = new Hibernate();
        this.users = this.personORM.selectAllPeople();
        this.printer = new Printer();
        this.worker = new Worker();
        this.adminManager = new AdminManager();
        this.auxiliarManager = new AuxiliarManager();
        this.vetManager = new VetManager();
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
        boolean exit = false;

        while (!exit) {
            try {
                String user = worker.askString("Introduce your user: ");
                String password = worker.askString("Introduce your password");
                User user1 = checkUser(user, password);
                int x = checkType();
                System.out.println(user1.getUserType());
                profile(user1.getUserType());
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int checkType() {
        for (User user : this.users) {
            if (user.getUserType() == 1 || user.getUserType() == 2 || user.getUserType() == 3) {
                return user.getUserType();
            }
        }
        return 0;
    }

    private User checkUser(String username, String password) throws MyException {
        for (User user : this.users) {
            if (user.getName().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new MyException(MyException.USER_PASS_INCORRECT);
    }

    private void profile(int type) {
        switch (type) {
            case 1:
                this.auxiliarManager.mainmenu();
                break;
            case 2:
                this.vetManager.mainmenu();
                break;
            case 3:
                this.adminManager.mainmenu(this.users);
                break;
        }
    }
}