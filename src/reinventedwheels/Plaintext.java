package reinventedwheels;

/**
 * A quite unnecessary reworking of <code>String</code>.
 * @author Alonso del Arte
 */
class Plaintext {

    private final char[] characters;

    Plaintext toLowerCase() {
        char[] copy = this.characters.clone();
        for (int i = 0; i < copy.length; i++) {
            copy[i] = Character.toLowerCase(copy[i]);
        }
        return new Plaintext(copy);
    }

    @Override
    public String toString() {
        return new String(this.characters);
    }

    Plaintext(char[] chars) {
        this.characters = chars;
    }

}
