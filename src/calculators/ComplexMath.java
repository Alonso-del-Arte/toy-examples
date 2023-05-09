package calculators;

import numerics.ComplexNumber;

public class ComplexMath {

    public static ComplexNumber sqrt(ComplexNumber z) {
        double a = z.getRealPart();
        double b = z.getImagPart();
        if (b == 0) {
            return new ComplexNumber(0.0, 1.0);
        }
        double norm = z.abs();
        double re = Math.sqrt((norm + a) / 2);
        double im = Math.sqrt((norm - a) / 2) * (b / Math.abs(b));
        return new ComplexNumber(re, im);
    }

}
