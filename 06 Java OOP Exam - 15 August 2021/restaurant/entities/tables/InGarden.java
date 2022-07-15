package restaurant.entities.tables;

public class InGarden extends BaseTable {
    private final static double pricePerPersonGarden = 4.50;

    public InGarden(int number, int size) {
        super(number, size, pricePerPersonGarden);
    }
}