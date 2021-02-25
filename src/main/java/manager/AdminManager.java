package manager;

import exception.MyException;
import hibernate.ExpedientORM;
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
    private ExpedientORM expedientORM;

    private List<Expedient> expedients;

    public AdminManager() {
        this.expedientORM = new ExpedientORM();
//        this.expedients = new ArrayList<>();
        this.expedients = this.expedientORM.selectAllExpedients();
        this.printer = new Printer();
        this.worker = new Worker();
    }

    public void mainmenu(List<User> users, boolean exit, int userId) {
        try {
            while (!exit) {
                this.printer.printMainMenu();
                switch (worker.askInt("Introduce an option: ")) {
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
                        this.registerUser();
                        break;
                    case 6:
                        this.unsubscribeUser();
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
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private void consultRecords(List<User> users) {
        System.out.println("Information about the users registered");
        users.forEach(user -> System.out.println(user.getId() + " " + user.getName() + " " + user.getSurname()));
        int id = worker.askInt("Introduce the id of the user do you want to consult: ");
        users.forEach(user -> {
            if (user.getId() == id) System.out.println(user.toString());
        });
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

    private void unsubscribeRecord() throws MyException {
        AtomicInteger counter = new AtomicInteger(1);
        this.expedients.forEach(expedient -> {
            System.out.println(counter + " - " + expedient.toString());
            counter.getAndIncrement();
        });
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

    private void editRecord() {

    }

    private void registerUser() {
        System.out.println("Register user");
        int id = worker.askInt("Introduce the id of the user");
        String name = worker.askString("Introduce the name of the user");
        String surname = worker.askString("Introduce the surname of the user");
        String password = worker.askString("Introduce the password:");
        String tuition = worker.askString("Introduce your tuition");
        int type = worker.askInt("Introduce the type of the user");
//        int year = worker.askInt("Introduce the day");
//        int month = worker.askInt("Introduce the month");
//        int day = worker.askInt("Introduce the day");
//        Date date = new Date(year,month,day);

    }

    private void unsubscribeUser() {

    }

    private void editUser() {

    }

    private void consultUser() {

    }
}
