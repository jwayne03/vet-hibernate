package exception;

import java.util.Arrays;
import java.util.List;

public class MyException extends Exception {

    public static final int USER_PASS_INCORRECT = 0;
    public static final int THIS_EXPEDIENT_DOESNT_EXIST = 1;
    public static final int WRONG_OPTION = 2;
    public static final int NO_EXPEDIENTS_AVAILABLE = 3;

    private final int value;

    public MyException(int value) {
        this.value = value;
    }

    private final List<String> message = Arrays.asList(
            "Username or password incorrect",
            "This expedient doesn't exist",
            "Wrong option, please chose someone where is in the range",
            "This user doesn't have expedients registered",
            "There are not expedients right now"
    );

    @Override
    public String getMessage() {
        return message.get(value);
    }
}
