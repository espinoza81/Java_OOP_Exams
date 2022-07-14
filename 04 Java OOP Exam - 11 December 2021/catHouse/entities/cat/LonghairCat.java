package catHouse.entities.cat;

public class LonghairCat extends BaseCat {
    //Can only live in LongHouse!

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
        setKilograms(9);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + 3);
    }
}
