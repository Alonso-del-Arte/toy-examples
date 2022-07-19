package textops;

import java.util.Arrays;

public class PalindromeChecker {

    private boolean caseSensitive = true;

    // TODO: Remove this field altogether
    private int sensitivityChangeCount = 0;

    public boolean isCaseSensitive() {
        return this.caseSensitive;
    }

    public void setCaseSensitivity(boolean sensitivity) {
        if (this.sensitivityChangeCount == 0) {
            this.caseSensitive = sensitivity;
            this.sensitivityChangeCount++;
        }
        //this.caseSensitive = sensitivity;
    }

    public boolean isPalindromic(String term) {
        StringBuilder builder = new StringBuilder(term);
        builder.reverse();
        return term.equals(builder.toString());
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
