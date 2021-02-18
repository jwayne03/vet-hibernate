package exception;

import java.util.Arrays;
import java.util.List;

public class MyException extends Exception {

    public static final int USER_PASS_INCORRECT = 0;

    private final int value;

    public MyException(int value) {
        this.value = value;
    }

    private final List<String> message = Arrays.asList(
            "Username or password incorrect"
    );

    @Override
    public String getMessage() {
        return message.get(value);
    }
}
