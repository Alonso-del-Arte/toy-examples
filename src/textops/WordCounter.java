package textops;

public class WordCounter {

    private final String text;

    // TODO: Write tests for this
    public String getText() {
        return "SORRY, NOT IMPLEMENTED YET";
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
