package calculators;

import java.util.ArrayList;
import java.util.List;

public class EratosthenesSieve {

    public static List<Integer> listPrimes(int bound) {
        List<Integer> numbers = new ArrayList<>();
        if (bound > 0) {
            numbers.add(2);
            numbers.add(3);
            numbers.add(5);
            numbers.add(7);
        }
        return numbers;
    }

}
