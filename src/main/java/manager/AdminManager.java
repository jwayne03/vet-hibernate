package manager;

import exception.MyException;
import hibernate.ExpedientORM;
import hibernate.PersonORM;
import model.Expedient;
import model.User;
import utils.Printer;
import worker.Worker;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AdminManager {

    private final Printer printer;
    private final Worker worker;
    private final ExpedientORM expedientORM;
    private final PersonORM personORM;

    private final List<Expedient> expedients;

    public AdminManager() {
        this.expedientORM = new ExpedientORM();
        this.personORM = new PersonORM();
        this.expedients = this.expedientORM.selectAllExpedients();
        this.printer = new Printer();
        this.worker = new Worker();
    }

    public void mainmenu(List<User> users, boolean exit, int userId) {
        while (!exit) {
            try {
                this.printer.printMainMenu();
                switch (this.worker.askInt("Introduce an option: ")) {
                    case 1:
                        this.consultRecords(users);
                        break;
                    case 2:
                        this.registerRecord(userId);
                        break;
                    case 3:
                        this.unsubscribeRecord();
                        break;
                    case 4:
                        this.editRecord();
                        break;
                    case 5:
                        this.registerUser(users);
                        break;
                    case 6:
                        this.unsubscribeUser(users);
                        break;
                    case 7:
                        this.editUser();
                        break;
                    case 8:
                        this.consultUser();
                        break;
                    case 0:
                        this.printer.printExit();
                        exit = true;
                    default:
                        this.printer.printNeedToIntroduceAnOption();
                        break;
                }
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void consultRecords(List<User> users) {
        System.out.println("Information about the users registered");
        users.forEach(user -> System.out.println("ID --> " + user.getId() + " " + user.getName() + " " + user.getSurname()));
        int id = worker.askInt("Introduce the id of the user do you want to consult: ");

        for (User user : users) {
            if (user.getId() == users.get(id - 1).getId()) {
                System.out.println(user.toString());
            } else {
                System.out.println("This expedient doesn't exist");
                return;
            }
        }
    }

    //TODO: fix the transaction
    private void registerRecord(int userId) {
        System.out.println(userId);
        String name = worker.askString("Introduce your name:");
        String surname = worker.askString("Introduce your surname");
        String dni = worker.askString("Introduce your DNI");
        int numberOfPets = worker.askInt("Introduce the number of your pets");
        System.out.println("The date format it will be: yy/mm/dd");
        int day = worker.askInt("Introduce the year:");
        int month = worker.askInt("Introduce the month:");
        int year = worker.askInt("Introduce the day:");
        Date date = new java.sql.Date(year, month, day);
        int phone = worker.askInt("Introduce your phone number: ");
        String postalcode = worker.askString("Introduce your postal code");
        this.addRecord(userId, name, surname, dni, numberOfPets, date, phone, postalcode);
    }

    private void addRecord(int userId, String name, String surname, String dni, int numberOfPets, Date date, int phone, String postalcode) {
        this.expedients.add(new Expedient(0, name, surname, dni, numberOfPets, date, postalcode, phone, userId));
        this.expedientORM.insertExpedient(0, name, surname, dni, numberOfPets, date, postalcode, phone, userId);
    }

    private void showRecords() {
        AtomicInteger counter = new AtomicInteger(1);
        this.expedients.forEach(expedient -> {
            System.out.println(counter + " - " + expedient.toString());
            counter.getAndIncrement();
        });
    }

    private void unsubscribeRecord() throws MyException {
        this.showRecords();
        System.out.println("What expedient do you want to unsubscribe? ");
        int option = worker.askInt("Introdice the expedient do you want to remove: ");
        this.isExpedientExist(option);
        this.expedientORM.deleteExpedient();
        this.removeExpedient(option);
    }

    private void isExpedientExist(int option) throws MyException {
        for (Expedient expedient : this.expedients) {
            if (expedient.getId() == this.expedients.get(option - 1).getId()) {
                System.out.println(this.expedients.get(option - 1));
                return;
            } else throw new MyException(MyException.THIS_EXPEDIENT_DOESNT_EXIST);
        }
    }

    private void removeExpedient(int option) throws MyException {
        for (Expedient expedient : this.expedients) {
            if (expedient.getId() == option) {
                this.expedients.remove(expedient.getId());
                return;
            } else throw new MyException(MyException.THIS_EXPEDIENT_DOESNT_EXIST);
        }
    }

    // TODO
    private void editRecord() {

    }

    private void registerUser(List<User> users) {
        System.out.println("Register user");
        String name = worker.askString("Introduce the name of the user");
        String surname = worker.askString("Introduce the surname of the user");
        String password = worker.askString("Introduce the password:");
        String dni = worker.askString("Introduce the DNI");
        String tuition = worker.askString("Introduce your tuition");
        int type = worker.askInt("Introduce the type of the user");
        this.addUser(users, name, surname, password, dni, tuition, type);
    }

    private void addUser(List<User> users, String name, String surname, String password, String dni, String tuition, int type) {
        this.personORM.insertNewUser(0, name, surname, password, dni, tuition, type, null);
        users.add(new User(0, name, surname, password, dni, tuition, type, null));
    }

    private void unsubscribeUser(List<User> users) throws MyException {
        int count = 1;
        for (User user : users) System.out.println(count++ + " - " + user.toString());
        int option = worker.askInt("What user do you want to delete?");
        this.removeUser(users, option);
    }

    // TODO: delete user and if has an expedient give to the person who registered that user
    private void removeUser(List<User> users, int option) throws MyException {
        for (User user : users) {
            if (option <= 0) throw new MyException(MyException.WRONG_OPTION);
            if (option < users.size()) {
                if (user.getId() == users.get(option - 1).getId()) {
                    for (Expedient expedient : this.expedients) {
                        if (user.getId() == expedient.getId_user_up()) {
                            System.out.println(user.getId());
                        }
                    }
                }
            } else throw new MyException(MyException.WRONG_OPTION);
        }
    }

    private void editUser() {

    }

    private void consultUser() {

    }
}
