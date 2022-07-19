package garage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GarageTests {
    private final static int CAR_COUNT_IN_GARAGE = 4;
    private final static int CAR_TO_FIND_MAX_SPEED_ABOVE = 150;
    private final static String CAR_TO_FIND_BY_BRAND = "Skoda";

    private Garage garage;
    private Car firstCar;
    private Car secondCar;
    private Car thirdCar;
    private Car fourCar;

    @Before
    public void setUp(){
        garage = new Garage();

        firstCar = new Car("Porsche", 320, 123456.78);
        secondCar = new Car("Nissan", 160, 8765.90);
        thirdCar = new Car("Skoda", 80, 200.20);
        fourCar = new Car("Skoda", 98, 350.60);

        garage.addCar(firstCar);
        garage.addCar(secondCar);
        garage.addCar(thirdCar);
        garage.addCar(fourCar);
    }

    @Test
    public void getCountOfCarInGarage(){

        Assert.assertEquals(CAR_COUNT_IN_GARAGE, garage.getCount());
    }

    @Test
    public void findAllCarsWithMaxSpeedAbove(){

        List<Car> expected = List.of(
                firstCar,
                secondCar
        );

        List<Car> actual = garage.findAllCarsWithMaxSpeedAbove(CAR_TO_FIND_MAX_SPEED_ABOVE);

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullCarInGarage() {

        garage.addCar(null);
    }

    @Test
    public void getTheMostExpensiveCar(){

        Assert.assertEquals(firstCar, garage.getTheMostExpensiveCar());
    }

    @Test
    public void findAllCarsByBrandSkoda(){
        List<Car> expected = List.of(
                thirdCar,
                fourCar
        );

        List<Car> actual = garage.findAllCarsByBrand(CAR_TO_FIND_BY_BRAND);

        Assert.assertEquals(expected, actual);
    }
}