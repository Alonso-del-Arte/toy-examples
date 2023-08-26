package trading;

public class StockMarket {

    private final String fullName, abbreviation;

    public String getName() {
        return this.fullName;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    // TODO: Write tests for this
    public StockMarket(String name) {
        this(name, "SNIY");
    }

    /**
     * Primary constructor.
     * @param name The name of the stock market. For example,
     *             "London Stock Exchange". Must not be null, must not be empty.
     * @param abbrev The acronym for the stock market. For example, "LSE". Must
     *               not be null, must not be empty and must be of shorter
     *               length than the name, but there is no other validation.
     *               Thus in this example there would be no problem with "LSX"
     *               or even an abbreviation using no characters from the name
     *               at all.
     * @throws IllegalArgumentException If the name or abbreviation is empty, or
     * if the abbreviation is longer than the name.
     * @throws NullPointerException If either the name or the abbreviation is
     * null.
     */
    public StockMarket(String name, String abbrev) {
        if (name == null || abbrev == null) {
            String excMsg = "Name, abbreviation must not be null";
            throw new NullPointerException(excMsg);
        }
        if (name.isEmpty() || abbrev.isEmpty()) {
            String excMsg = "Name, abbreviation must not be empty";
            throw new IllegalArgumentException(excMsg);
        }
        int nameLen = name.length();
        int abbrevLen = abbrev.length();
        if (nameLen < abbrevLen) {
            int diff = abbrevLen - nameLen;
            String excMsg = "Not valid for abbreviation " + abbrev
                    + " to be longer than name " + name + " by " + diff
                    + " characters";
            throw new IllegalArgumentException(excMsg);
        }
        this.fullName = name;
        this.abbreviation = abbrev;
    }

}
