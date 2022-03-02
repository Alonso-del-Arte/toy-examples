package calculators;

import java.util.ArrayList;
import java.util.List;

public class IntegerMath {

    public static boolean isPrime(int n) {
        int number = Math.abs(n);
        if (number == 0 || number == 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        boolean noDivisorFound = true;
        double root = Math.sqrt(number);
        int potentialDivisor = 3;
        while (potentialDivisor <= root && noDivisorFound) {
            noDivisorFound = number % potentialDivisor != 0;
            potentialDivisor += 2;
        }
        return noDivisorFound;
    }

    public static int randomPrime(int bound) {
        if (bound > 0) return 2;
        return -2;
    }

    // TODO: Write tests for this
    public static long euclideanGCD(long a, long b) {
        return -1L;
    }

}
