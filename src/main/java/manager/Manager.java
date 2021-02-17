package manager;

import hibernate.Hibernate;
import model.User;
import utils.Printer;
import utils.UserType;
import worker.Worker;

import java.util.Date;
import java.util.List;

public class Manager {

    private static Manager manager;
    private Printer printer;
    private Worker worker;
    private List<User> users;
    private Hibernate hibernate;

    private Manager() {
        this.hibernate = new Hibernate();
        this.users = this.hibernate.selectAllUser();
        this.printer = new Printer();
        this.worker = new Worker();
    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    public void execute() {
        System.out.println(this.users.toString());
        this.printer.printLoginMessage();

        switch (worker.askInt("Introduce an option")) {
            case 1:
                this.login();
                break;
            case 2:
                this.register();
                break;
            default:
                this.printer.printNeedToIntroduceAnOption();
                break;
        }
    }

    private void login() {

    }

    private void register() {
        this.printer.printRegisterMessage();
        String name = worker.askString("Introduce your name: ");
        String surname = worker.askString("Introduce your surname: ");
        String dni = worker.askString("Introduce your DNI: ");
        String tuition = worker.askString("Introduce your tuition: ");
        String password = worker.askString("Introduce your password: ");
        String confirmPassword = worker.askString("Confirm your password: ");
        this.printer.printRole();
        int type = worker.askInt("What is your role?");
        Date lastaccess = null;

        this.hibernate.registerUser(name, surname, dni, tuition, password, type, lastaccess);
    }


}
