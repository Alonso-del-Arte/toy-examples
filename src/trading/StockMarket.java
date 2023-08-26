package trading;

public class StockMarket {

    private final String fullName;

    // TODO: Write tests for this
    public String getName() {
        switch (this.fullName) {
            case "New York Stock Exchange":
                return "New York Stock Exchange";
            case "NYSE Chicago":
                return "NYSE Chicago";
            case "National Association of Securities Dealers Automated Quotations":
                return "National Association of Securities Dealers Automated Quotations";
            default:
                return "SORRY, NOT IMPLEMENTED YET";
        }

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
