package restaurant.entities.tables;

public class Indoors extends BaseTable {
    private final static double pricePerPersonIndoors = 3.50;

    public Indoors(int number, int size) {
        super(number, size, pricePerPersonIndoors);
    }
}