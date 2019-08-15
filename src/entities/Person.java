package entities;

public class Person extends Entity {

    private String personName;
    private Address personAddress;

    public Person(String name, Address address) {
        this.personName = name;
        this.personAddress = address;
    }

}
