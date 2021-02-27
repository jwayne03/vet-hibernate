package manager;

import model.User;
import utils.Printer;
import worker.Worker;

import java.util.List;

public class AuxiliarManager {

    private Printer printer;
    private Worker worker;

    public AuxiliarManager() {
        this.printer = new Printer();
        this.worker = new Worker();
    }

//    public void mainmenu(List<User> users) {
//        this.consultRecords(users);
//    }


}
