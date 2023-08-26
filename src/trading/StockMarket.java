package trading;

public class StockMarket {

    private final String fullName, abbreviation;

    public String getName() {
        return this.fullName;
    }

    // TODO: Write tests for this
    public String getAbbreviation() {
        switch (this.abbreviation) {
            case "NYSE":
                return "NYSE";
            case "CHX":
                return "CHX";
            case "NASDAQ":
                return "NASDAQ";
            default:
                return "SORRY, NOT IMPLEMENTED YET";
        }
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
