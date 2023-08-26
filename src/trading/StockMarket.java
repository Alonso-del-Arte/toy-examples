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

    public StockMarket(String name, String abbrev) {
        // TODO: Write tests for this
        this.fullName = name;
        this.abbreviation = abbrev;
    }

}
