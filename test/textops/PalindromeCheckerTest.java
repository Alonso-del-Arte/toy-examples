package textops;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PalindromeCheckerTest {

    @Test
    void testIsPalindromic() {
        System.out.println("isPalindromic");
        PalindromeChecker checker = new PalindromeChecker();
        String word = "kayak";
        String msg = "\"" + word + "\" should be considered palindromic";
        assert checker.isPalindromic(word) : msg;
    }

    @Test
    void testIsNotPalindromic() {
        PalindromeChecker checker = new PalindromeChecker();
        String word = "canoe";
        String msg = "\"" + word + "\" should not be considered palindromic";
        assert !checker.isPalindromic(word) : msg;
    }

    @Test
    void testCaseSensitivityCanBeToggled() {
        PalindromeChecker checker = new PalindromeChecker();
        boolean originalCaseSensitivity = checker.isCaseSensitive();
        boolean otherCaseSensitivity = !originalCaseSensitivity;
        checker.setCaseSensitivity(otherCaseSensitivity);
        String toggleMsg = "Should be able to toggle case sensitivity from "
                + originalCaseSensitivity + " to " + otherCaseSensitivity;
        assert otherCaseSensitivity == checker.isCaseSensitive() : toggleMsg;
        checker.setCaseSensitivity(originalCaseSensitivity);
        String msg = "Should be able to toggle case sensitivity to "
                + originalCaseSensitivity + " back from "
                + otherCaseSensitivity;
        assert originalCaseSensitivity == checker.isCaseSensitive() : msg;
    }

    @Test
    void testConstructorSetsCaseSensitivityToTrue() {
        PalindromeChecker checker = new PalindromeChecker();
        String msg = "Newly constructed " + checker
                + " should be case-sensitive";
        assert checker.isCaseSensitive() : msg;
    }

}
