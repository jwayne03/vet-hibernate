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
                        this.consultRecords();
                        break;
                    case 2:
                        this.registerRecord(userId);
                        break;
                    case 3:
                        this.unsubscribeRecord();
                        break;
                    case 4:
                        // TODO
                        this.editRecord();
                        break;
                    case 5:
                        this.registerUser(users);
                        break;
                    case 6:
                        this.unsubscribeUser(users);
                        break;
                    case 7:
                        // TODO
                        this.editUser(users);
                        break;
                    case 8:
                        this.consultUser(users);
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

    private void consultRecords() throws MyException {
        AtomicInteger count = new AtomicInteger(1);
        System.out.println("What record do you want to consult?");
        this.expedients.forEach(expedient -> System.out.println(count.getAndIncrement() + " - " + expedient.toStringIdNameSurname()));
        int option = worker.askInt("What expedient do you want to see?");
        this.showExpedient(option);
    }

    private void showExpedient(int option) throws MyException {
        if (option <= 0 || option > this.expedients.size()) throw new MyException(MyException.WRONG_OPTION);
        for (Expedient expedient : this.expedients) {
            if (this.expedients.get(option - 1).getId() == expedient.getId()) {
                System.out.println(expedient.toString());
            }
        }
    }

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
        System.out.println("The record has been registered successfully");
    }

    private void showUsers() {
        AtomicInteger counter = new AtomicInteger(1);
        this.expedients.forEach(expedient -> {
            System.out.println(counter + " - " + expedient.toString());
            counter.getAndIncrement();
        });
    }

    private void unsubscribeRecord() throws MyException {
        this.showUsers();
        System.out.println("What expedient do you want to unsubscribe? ");
        int option = worker.askInt("Introdice the expedient do you want to remove: ");
        this.isExpedientExist(option);
        this.expedientORM.deleteExpedient();
        this.removeExpedient(option);
    }

    private boolean isExpedientExist(int option) throws MyException {
        for (Expedient expedient : this.expedients) {
            if (expedient.getId() == this.expedients.get(option - 1).getId()) {
                System.out.println(this.expedients.get(option - 1));
                return true;
            }
        }
        throw new MyException(MyException.THIS_EXPEDIENT_DOESNT_EXIST);
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
    private void editRecord() throws MyException {
        AtomicInteger count = new AtomicInteger(1);
        System.out.println("Edit record");
        if (this.expedients.isEmpty()) throw new MyException(MyException.NO_EXPEDIENTS_AVAILABLE);
        else this.expedients.forEach(expedient -> System.out.println(count.getAndIncrement() + " - " + expedient.toString()));
        int option = worker.askInt("What expedient do you want to update?");
        this.isExpedientExist(option);
        if (this.checkExpedient(option)) this.updateExpedient(option);
    }

    private void updateExpedient(int option) {
        for (Expedient expedient : this.expedients) {
            if (this.expedients.get(option - 1).getId() == expedient.getId()) {
                int numberOfPets = worker.askInt("How many pets do you have?");
                int phone = worker.askInt("Introduce your phone number");
                String postalcode = worker.askString("Introduce your postal code");
                this.expedientORM.updateExpedient(expedient, numberOfPets, phone, postalcode);
                return;
            }
        }
    }

    private boolean checkExpedient(int option) throws MyException {
        for (Expedient expedient : this.expedients) {
            if (this.expedients.get(option - 1).getId() == expedient.getId()) {
                return true;
            }
        }
        throw new MyException(MyException.WRONG_OPTION);
    }

    private void registerUser(List<User> users) {
        System.out.println("Register user");
        String name = worker.askString("Introduce the name of the user");
        String surname = worker.askString("Introduce the surname of the user");
        String dni = worker.askString("Introduce the DNI");
        String tuition = worker.askString("Introduce your tuition");
        String password = worker.askString("Introduce the password:");
        int type = worker.askInt("Introduce the type of the user");
        this.addUser(users, name, surname, dni, tuition, password, type);
    }

    private void addUser(List<User> users, String name, String surname, String dni, String tuition, String password, int type) {
        this.personORM.insertNewUser(users.size() + 1, name, surname, dni, tuition, password, type);
        users.add(new User(users.size() + 1, name, surname, dni, tuition, password, type, null));
    }

    private void unsubscribeUser(List<User> users) throws MyException {
        int count = 1;
        for (User user : users) System.out.println(count++ + " - " + user.toString());
        int option = worker.askInt("What user do you want to delete?");
        this.removeUser(users, option);
    }

    // TODO: delete the user and check if has an expedient to give to the person who registered that user
    private void removeUser(List<User> users, int option) throws MyException {
        for (User user : users) {
            if (option <= 0 || option > users.size()) throw new MyException(MyException.WRONG_OPTION);
            if (users.get(option - 1).getId() == user.getId()) {
                for (Expedient expedient : this.expedients) {
                    if (user.getId() == expedient.getId_user_up()) {
                        System.out.println(user.toString());
                    }
                    return;
                }
            }
        }
    }

    private void editUser(List<User> users) throws MyException {
        users.forEach(user -> System.out.println("ID --> [" + user.getId() + "] " + user.getName() + " " + user.getSurname()));
        int option = worker.askInt("Introduce the the user do you want to modify: ");
        this.printer.printTypes();

        for (User user : users) {
            if (option <= 0 || option > users.size()) throw new MyException(MyException.WRONG_OPTION);
            this.findUserByIdUpdate(users, option, user);
        }
    }

    private void findUserByIdUpdate(List<User> users, int option, User user) throws MyException {
        if (users.get(option - 1).getId() == user.getId()) {
            int type = worker.askInt("Introduce the user type");
            String password = worker.askString("Introduce your the new password:");
            String confirmPassword = worker.askString("Confirm the new password:");

            this.confirmPassword(user, type, password, confirmPassword);
        }
    }

    private boolean confirmPassword(User user, int type, String password, String confirmPassword) throws MyException {
        if (password.equals(confirmPassword)) {
            this.personORM.updateUser(user, type, password);
            return true;
        } else throw new MyException(MyException.PASSWORD_INCORRECT);
    }

    private void consultUser(List<User> users) throws MyException {
        System.out.println("Information about the users registered");
        users.forEach(user -> System.out.println("ID --> [" + user.getId() + "] " + user.getName() + " " + user.getSurname()));
        int option = worker.askInt("Introduce the id of the user do you want to consult: ");
        this.showUsers(users, option);
    }

    private void showUsers(List<User> users, int option) throws MyException {
        for (User user : users) {
            if (option <= 0 || option > users.size()) throw new MyException(MyException.WRONG_OPTION);
            if (users.get(option - 1).getId() == user.getId()) {
                System.out.println(user.toString());
            }
        }
    }
}
