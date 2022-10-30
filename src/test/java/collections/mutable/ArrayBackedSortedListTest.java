package collections.mutable;

import java.math.BigInteger;
import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayBackedSortedListTest {

    private static final int USUAL_INITIAL_CAPACITY = 10;

    private static final Random RANDOM = new Random();

    @Test
    void testAdd() {
        System.out.println("add");
        ArrayBackedSortedList<BigInteger> sortedList
                = new ArrayBackedSortedList<>(USUAL_INITIAL_CAPACITY);
        BigInteger number = new BigInteger(84, RANDOM);
        String msg = "Should be able to add " + number.toString()
                + " to sorted list";
        boolean opResult = sortedList.add(number);
        assert opResult : msg;
    }

}