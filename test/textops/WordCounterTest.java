package textops;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class WordCounterTest {

    @Test
    void testWordCountZeroForEmptyString() {
        WordCounter instance = new WordCounter("");
        int expected = 0;
        int actual = instance.wordCount();
        String message = "Word count for empty String should be 0";
        assertEquals(expected, actual, message);
    }

}