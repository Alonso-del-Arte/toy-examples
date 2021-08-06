package collections;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayBackedListTest {

    @Test
    void testAdd() {
        System.out.println("add");
        ArrayBackedList<Locale> list = new ArrayBackedList<>();
        Locale locale = Locale.getDefault();
        String msg = "Should be able to add " + locale.toString() + " to list of locales";
        boolean opResult = list.add(locale);
        assert opResult : msg;
    }

}