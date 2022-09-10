package animals.canines;

public class Dog extends Canine {

    public final String species() {
        return "Canis lupus";
    }

    @Override
    public String getTaxonomicClassification() {
        return "Canis familiaris";
    }

    public void bark() {
        System.out.println("Arf! Arf!");
    }

}
