package textops;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import randomness.ExtendedRandom;

class WordCounterTest {

    private static final String[] WORDS = {"brown", "dog", "fox", "jumps",
            "lazy", "over", "quick", "the"};

    private static final int NUMBER_OF_WORDS = WORDS.length;

    @Test
    void testWordCountZeroForEmptyString() {
        WordCounter instance = new WordCounter("");
        int expected = 0;
        int actual = instance.wordCount();
        String message = "Word count for empty String should be 0";
        assertEquals(expected, actual, message);
    }

    private static String makePhrase(int len) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = (i * ExtendedRandom.nextInt(128)) % NUMBER_OF_WORDS;
            builder.append(WORDS[index]);
            builder.append(' ');
        }
        return builder.toString();
    }

    @Test
    void testWordCount() {
        System.out.println("wordCount");
        int expected = ExtendedRandom.nextInt(16) + 4;
        String phrase = makePhrase(expected);
        WordCounter instance = new WordCounter(phrase);
        int actual = instance.wordCount();
        String message = "Counting words in \"" + phrase + "\"";
        assertEquals(expected, actual, message);
    }

}