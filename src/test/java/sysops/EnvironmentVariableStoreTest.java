package sysops;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentVariableStoreTest {

    @Test
    void testGetVariable() {
        System.out.println("getVariable");
        String label = "OS";
        String expected = System.getenv(label);
        String actual = EnvironmentVariableStore.getVariable(label);
        assertEquals(expected, actual);
    }

    @Test
    void testGetNonexistentVariable() {
        String badLabel = "There should be no variable for this label";
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            String badVariable = EnvironmentVariableStore.getVariable(badLabel);
            System.out.println("Bad label \"" + badLabel
                    + "\" should not have retrieved variable \"" + badVariable
                    + "\"");
        });
        System.out.println("Trying to access variable for bad label \""
                + badLabel + "\" correctly caused NoSuchElementException");
        String excMsg = exception.getMessage();
        assert excMsg != null : "Message should not be null";
        System.out.println("\"" + excMsg + "\"");
    }

}