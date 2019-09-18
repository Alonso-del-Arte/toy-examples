package reinventedwheels;

public class WrappedString {

    private final String heldStr;

    @Override
    public String toString() {
        return this.heldStr;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        WrappedString that = (WrappedString) obj;
        return this.heldStr.equals(that.heldStr);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public WrappedString toAllCaps() {
        char[] letters = this.heldStr.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            letters[i] = (char) (letters[i] - ' ');
        }
        return new WrappedString(new String(letters));
    }

    public WrappedString(String s) {
        this.heldStr = s;
    }

}
