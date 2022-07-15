package restaurant.entities.healthyFoods;

public class VeganBiscuits extends Food{
    private final static double InitialVeganBiscuitsPortion  = 205;

    public VeganBiscuits(String name, double price) {
        super(name, InitialVeganBiscuitsPortion, price);
    }
}