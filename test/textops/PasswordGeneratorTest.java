package textops;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    @Test
    void testGenerate() {
        System.out.println("generate");
        String expression = "(?=^.{8,}$)(?=.*\\d)(?=.*[!@#$%^&*]+)"
                + "(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
        Pattern regex = Pattern.compile(expression);
        PasswordGenerator generator = new PasswordGenerator(new SecureRandom());
        String password = generator.generate(regex);
        Matcher matcher = regex.matcher(password);
        boolean opResult = matcher.matches();
        String msg = "Generated password \"" + password
                + "\" should match regular expression \"" + expression + "\"";
        assert opResult : msg;
    }

    @Test
    void testGenerateAnew() {
        String expression = "[a-zA-Z]{8}";
        Pattern regex = Pattern.compile(expression);
        SecureRandom random = new SecureRandom();
        PasswordGenerator generator = new PasswordGenerator(random);
        int expected = random.nextInt(16) + 4;
        Set<String> passwords = new HashSet<>(expected);
        for (int i = 0; i < expected; i++) {
            passwords.add(generator.generate(regex));
        }
        String msg = "Calling generate() " + expected
                + " times should have given " + expected
                + " distinct passwords";
        int actual = passwords.size();
        assertEquals(expected, actual, msg);
    }

}