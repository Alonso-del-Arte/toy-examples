package textops;

import java.security.SecureRandom;
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

}