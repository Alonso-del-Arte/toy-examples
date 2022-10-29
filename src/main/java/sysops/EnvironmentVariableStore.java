package sysops;

import java.util.Map;
import java.util.NoSuchElementException;

public class EnvironmentVariableStore {

    private static final Map<String, String> variables = System.getenv();

    public static String getVariable(String label) {
        if (variables.containsKey(label)) {
            return variables.get(label);
        } else {
            String excMsg = "No environment variable for label \"" + label
                    + "\"";
            throw new NoSuchElementException(excMsg);
        }
    }

}
