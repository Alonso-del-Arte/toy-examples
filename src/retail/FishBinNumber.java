package retail;

/**
 * Represents a fictional ISBN (FISBN or Fish Bin number).
 */
public class FishBinNumber extends BarcodeNumberWithCheckDigit {

    public static final long serialVersionUID = 4553372319738313728L;

    public FishBinNumber(long num) {
        super(num);
    }

}
