package trading;

public class StockMarket {

    private final String fullName;

    public String getName() {
        return this.fullName;
    }

    // TODO: Write tests for this
    public String getAbbreviation() {
        return "SORRY, NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    public StockMarket(String name) {
        this(name, "SNIY");
    }

    public StockMarket(String name, String abbrev) {
        // TODO: Write tests for this
        this.fullName = name;
    }

}
