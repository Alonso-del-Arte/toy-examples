package textops;

public class WordCounter {

    private final String text;

    public String getText() {
        return this.text;
    }

    public int wordCount() {
        int spaceCount = 0;
        int index = 0;
        while (index > -1) {
            index = this.text.indexOf(" ", index + 1);
            spaceCount++;
        }
        return spaceCount - 1;
    }

    public WordCounter(String s) {
        this.text = s;
    }

}
