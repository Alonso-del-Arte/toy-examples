package collections.mutable;

import java.util.NoSuchElementException;
import java.util.Random;

import javax.naming.ldap.BasicControl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    private static final Random RANDOM = new Random();

    @Test
    void testConstructorRejectsNegativeCapacity() {
        int badCapacity = -RANDOM.nextInt(512) - 1;
        Throwable t = assertThrows(IllegalArgumentException.class, () -> {
            Queue<BasicControl> queue = new Queue<>(badCapacity);
            System.out.println("Should not have been able to create "
                    + queue.toString() + " with capacity " + badCapacity);
        });
        String excMsg = t.getMessage();
        assert excMsg != null;
        System.out.println("\"" + excMsg + "\"");
    }

}