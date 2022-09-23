package measures.imperial;

import fractions.Fraction;

public class Mile extends LengthMeasure {

    private static final Fraction FOOT_MULTIPLIER = new Fraction(5280);

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

    public Mile(Fraction number) {
        super(number, FOOT_MULTIPLIER);
    }

}
