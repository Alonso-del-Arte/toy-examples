package textops;

import java.util.Locale;

/**
 * Checks whether <code>String</code> instances are palindromes or not. Includes
 * options for case-sensitivity.
 * @author Alonso del Arte
 */
public class PalindromeChecker {

    private boolean caseSensitive = true;

    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    public void setCaseSensitivity(boolean sensitivity) {
        this.caseSensitive = sensitivity;
    }

    private boolean checkPalindromic(String term) {
        StringBuilder builder = new StringBuilder(term);
        builder.reverse();
        return term.equals(builder.toString());
    }

    public boolean isPalindromic(String term) {
        if (this.caseSensitive) {
            return this.checkPalindromic(term);
        } else {
            return this.checkPalindromic(term.toLowerCase());
        }
    }

    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker();
        if (args.length > 0) {
            for (String arg : args) {
                switch (arg) {
                    case "-case":
                        checker.setCaseSensitivity(true);
                        break;
                    case "-noCase":
                        checker.setCaseSensitivity(false);
                        break;
                    case "-version":
                        System.out.println("Palindrome Checker Version 0.0");
                        break;
                    default:
                        System.out.print("\"" + arg + "\" is ");
                        if (!checker.isPalindromic(arg)) {
                            System.out.print("NOT ");
                        }
                        System.out.println("palindromic");
                }
            }
        }
    }

}
