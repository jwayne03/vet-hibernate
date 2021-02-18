package manager;

import model.User;
import utils.Printer;
import worker.Worker;

import java.util.List;

public class AdminManager {

    private Printer printer;
    private Worker worker;

    public AdminManager() {
        this.printer = new Printer();
        this.worker = new Worker();
    }

    public void mainmenu(List<User> users) {
        this.printer.printMainMenu();

        switch (worker.askInt("Introduce an option: ")) {
            case 1:
                consultRecords(users);
                break;
            case 2:
                registerRecord();
                break;
            case 3:
                unsubscribeRecord();
                break;
            case 4:
                editRecord();
                break;
            case 5:
                registerUser();
                break;
            case 6:
                unsubscribeUser();
                break;
            case 7:
                editUser();
                break;
            case 8:
                consultUser();
                break;
            default:
                this.printer.printNeedToIntroduceAnOption();
                break;
        }
    }

    private void consultRecords(List<User> users) {
        System.out.println("Information about the users registered");
        for (User user : users) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getSurname());
        }
        //int id = worker.askInt("Introduce the id of the user do you want to consult: ");
    }

    private void registerRecord() {

    }

    private void unsubscribeRecord() {

    }

    private void editRecord() {

    }

    private void registerUser() {

    }

    private void unsubscribeUser() {

    }

    private void editUser() {

    }

    private void consultUser() {

    }
}
