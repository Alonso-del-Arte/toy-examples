package entities;

public class Address {

    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String country;
    private final String postalCode;

    public Address(String line1, String line2, String city, String state, String country, String postalCode) {
        this.addressLine1 = line1;
        this.addressLine2 = line2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
    }

}
