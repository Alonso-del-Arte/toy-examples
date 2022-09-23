package measures.imperial;

import fractions.Fraction;

public class Yard extends LengthMeasure {

    private static final Fraction THREE = new Fraction(3);

    // TODO: Write tests for this
    @Override
    public String getSingularWord() {
        return "NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    @Override
    public String getPluralWord() {
        return "NOT IMPLEMENTED YET";
    }

    // TODO: Write tests for this
    @Override
    public String getAbbreviation() {
        return "NOT IMPLEMENTED YET";
    }

    public Yard(Fraction number) {
        super(number, THREE);
    }

}
