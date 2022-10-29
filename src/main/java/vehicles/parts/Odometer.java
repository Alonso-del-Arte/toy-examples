package vehicles.parts;

import fractions.Fraction;
import measures.imperial.Mile;

/**
 * Represents an odometer in a car. I frequently use this as an example of
 * encapsulation in object-oriented programming. The idea is that another object
 * can advance the mileage, but this class forbids rolling the odometer back.
 * @author Alonso del Arte
 */
public class Odometer {

    // TODO: Write tests for this
    public Mile getMileage() {
        return new Mile(new Fraction(Integer.MIN_VALUE));
    }

    // TODO: Write tests for this
    public void advanceMileage(Mile increment) {
        //
    }

    // TODO: Write tests for this
    public Odometer() {
        this(new Mile(new Fraction(Integer.MIN_VALUE)));
    }

    // TODO: Write tests for this
    public Odometer(Mile initialMileage) {
        //
    }

}
