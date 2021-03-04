package worker;

import java.util.ArrayList;
import java.util.List;

public interface WorkerInterface {

    String askString(String args);

    String askStringToLowerCase(String args);

    String askStringToUpperCase(String args);

    String askString(String args, String a, String b);

    boolean wordIsOk(String args, ArrayList<String> wordsAccepted);

    int askInt(String args);

    double askDouble(String args);

    double askDouble(String args, int min, int max);
}

