package football.entities.supplement;

public class Powdered extends BaseSupplement{
    private static final int ENERGY = 120;
    private static final double PRICE = 15.0;

    public Powdered() {
        super(ENERGY, PRICE);
    }
}