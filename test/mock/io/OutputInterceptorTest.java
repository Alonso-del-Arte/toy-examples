package mock.io;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import randomness.ExtendedRandom;

import static org.junit.jupiter.api.Assertions.*;

class OutputInterceptorTest {

    @Test
    void testGetText() {
        System.out.println("getText");
        int length = ExtendedRandom.nextInt(16) + 4;
        String expected = ExtendedRandom.alphanumeric(length);
        byte[] bytes = new byte[length];
        char[] characters = expected.toCharArray();
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) characters[i];
        }
        OutputInterceptor interceptor = new OutputInterceptor();
        try {
            interceptor.write(bytes);
            String actual = interceptor.getText();
            assertEquals(actual, expected);
        } catch (IOException ioe) {
            String msg = "Encountered IOException trying to write \""
                    + expected + "\", " + ioe.getMessage();
            fail(msg);
        }
    }

}
