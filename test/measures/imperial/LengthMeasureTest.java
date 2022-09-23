package measures.imperial;

import fractions.Fraction;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import randomness.ExtendedRandom;

class LengthMeasureTest {

    private static final Fraction TWENTY_TWO_SEVENTHS = new Fraction(22, 7);

    @Test
    void testReferentialEquality() {
        Fraction fraction = ExtendedRandom.nextFraction();
        LengthMeasure lengthMeasure = new LengthMeasureImpl(fraction);
        assertEquals(lengthMeasure, lengthMeasure);
    }

    private static class LengthMeasureImpl extends LengthMeasure {

        @Override
        public String getSingularWord() {
            return "length measure";
        }

        @Override
        public String getPluralWord() {
            return "length measures";
        }

        @Override
        public String getAbbreviation() {
            return "lmi";
        }

        LengthMeasureImpl(Fraction number) {
            super(number, TWENTY_TWO_SEVENTHS);
        }

    }

}