package textops;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generates passwords using a presumably secure source of pseudorandomness.
 * However, this class is for educational purposes only. We make no guarantees
 * as to the security or suitability of passwords generated by this program.
 * @author Alonso del Arte
 */
public class PasswordGenerator {

    // TODO: Write tests for this
    public String generate(Pattern regex) {
        return "01234Aa!";
    }

    public PasswordGenerator(SecureRandom random) {
        //
    }

}
