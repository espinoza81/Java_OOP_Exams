package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HouseTests {
    private final static String HOUSE_NAME = "Cat House";
    private final static int HOUSE_CAPACITY = 2;
    private final static int HOUSE_CAT_COUNT = 2;
    private final static String FIRST_CAT_NAME = "Tommy";
    private final static String SECOND_CAT_NAME = "Dusty";
    private final static String CAT_FOR_REMOVE_AND_SALE = "Dusty";
    private final static String CAT_HOUSE_STATISTICS = "The cat Tommy, Dusty is in the house Cat House!";
    private final static String NULL_CAT_FOR_REMOVE_AND_SALE = "Tim";


    private House house;
    private Cat firstCat;
    private Cat secondCat;

    @Before
    public void setUp(){
        house = new House(HOUSE_NAME, HOUSE_CAPACITY);

        firstCat = new Cat(FIRST_CAT_NAME);
        secondCat = new Cat(SECOND_CAT_NAME);

        house.addCat(firstCat);
        house.addCat(secondCat);
        house.removeCat("Dusty");
        house.addCat(secondCat);
    }

    @Test(expected = NullPointerException.class)
    public void createHouseWhitNullName(){

        House testHouse = new House(null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createHouseWhitNegativeCapacity(){

        House testHouse = new House("Test House", -5);
    }

    @Test
    public void getCatCountInHouse() {

        Assert.assertEquals(HOUSE_CAT_COUNT, house.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addCatInFullHouse() {

        house.addCat(firstCat);
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeCatWhitNameThatNotPresent() {

        house.removeCat(NULL_CAT_FOR_REMOVE_AND_SALE);
    }

    @Test (expected = IllegalArgumentException.class)
    public void saleCatWhitNameThatNotPresent() {

        house.catForSale(NULL_CAT_FOR_REMOVE_AND_SALE);
    }

    @Test
    public void saleExistingCat() {
        Cat testCat = house.catForSale(CAT_FOR_REMOVE_AND_SALE);

        Assert.assertEquals(secondCat, testCat);
        Assert.assertFalse(testCat.isHungry());
    }

    @Test
    public void getHouseStatistics() {
        String actual = house.statistics();

        Assert.assertEquals(CAT_HOUSE_STATISTICS, actual);
    }

    @Test
    public void getHouseName() {
        String actual = house.getName();

        Assert.assertEquals(HOUSE_NAME, actual);
    }

    @Test
    public void getHouseCapacity() {

        Assert.assertEquals(HOUSE_CAPACITY, house.getCapacity());
    }
}