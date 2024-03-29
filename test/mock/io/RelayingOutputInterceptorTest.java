package mock.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class RelayingOutputInterceptorTest {

    @Test
    void testWriteRelaysToRelayTarget() throws IOException {
        System.out.println("write");
        int length = ExtendedRandom.nextInt(32) + 8;
        String expected = ExtendedRandom.alphanumeric(length);
        byte[] bytes = new byte[length];
        char[] characters = expected.toCharArray();
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) characters[i];
        }
        OutputStream relayTarget = new ByteArrayOutputStream();
        OutputStream relay = new RelayingOutputInterceptor(relayTarget);
        relay.write(bytes);
        String actual = relayTarget.toString().replace("\n", "")
                .replace("\r", "");
        assertEquals(expected, actual);
    }

    @Test
    void testGetText() throws IOException {
        System.out.println("getText");
        int length = ExtendedRandom.nextInt(32) + 8;
        String expected = ExtendedRandom.alphanumeric(length);
        char[] characters = expected.toCharArray();
        RelayingOutputInterceptor interceptor
                = new RelayingOutputInterceptor(new ByteArrayOutputStream());
        for (char ch : characters) {
            interceptor.write(ch);
        }
        String actual = interceptor.getText();
        assertEquals(expected, actual);
    }

}
