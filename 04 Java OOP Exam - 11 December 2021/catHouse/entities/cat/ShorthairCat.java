package catHouse.entities.cat;

public class ShorthairCat extends BaseCat{
    //Can only live in ShortHouse!

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        setKilograms(7);
    }

    @Override
    public void eating() {
        setKilograms(getKilograms() + 1);
    }
}
