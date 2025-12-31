package textops;

public class SyllableCounter {

    private final String text;

    public String getText() {
        return this.text;
    }

    // TODO: Write tests for this
    public int syllableCount() {
        return -1;
    }

    public SyllableCounter(String s) {
        if (s == null) {
            String excMsg = "Text should not be null";
            throw new NullPointerException((excMsg));
        }
        this.text = s;
    }

}
