package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ShopTest {
    private static final String FIRST_GOOD_NAME = "Butter";
    private static final String FIRST_GOOD_CODE = "123";
    private static final String SECOND_GOOD_NAME = "Milk";
    private static final String SECOND_GOOD_CODE = "456";
    private static final String SHELVES_FOR_FIRST_GOOD = "Shelves1";
    private static final String EXISTING_NOT_TAKEN_SHELVES = "Shelves2";
    private static final String FAKE_SHELVES_NAME = "Fake Shelves";
    private static final String INFO_FOR_ADDING_GOOD = "Goods: 456 is placed successfully!";
    private static final String INFO_FOR_REMOVING_GOOD = "Goods: 123 is removed successfully!";

    private Shop shop;
    private Goods firstGood;
    private Goods secondGood;

    @Before
    public  void setUp() throws OperationNotSupportedException {
        shop = new Shop();
        firstGood = new Goods(FIRST_GOOD_NAME, FIRST_GOOD_CODE);
        secondGood = new Goods(SECOND_GOOD_NAME, SECOND_GOOD_CODE);

        shop.addGoods(SHELVES_FOR_FIRST_GOOD, firstGood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addGoodToNonExistingShelves() throws OperationNotSupportedException {

        shop.addGoods(FAKE_SHELVES_NAME, secondGood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addGoodToNotNullShelves() throws OperationNotSupportedException {

        shop.addGoods(SHELVES_FOR_FIRST_GOOD, secondGood);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void addExistingGood() throws OperationNotSupportedException {

        shop.addGoods(EXISTING_NOT_TAKEN_SHELVES, firstGood);
    }

    @Test
    public void addNonExistingGoodToExistingEmptyShelves() throws OperationNotSupportedException {

        Assert.assertEquals(INFO_FOR_ADDING_GOOD, shop.addGoods(EXISTING_NOT_TAKEN_SHELVES, secondGood));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeGoodFromNonExistingShelves() {

        shop.removeGoods(FAKE_SHELVES_NAME, firstGood);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeNonExistingGoodFromExistingShelves() {

        shop.removeGoods(SHELVES_FOR_FIRST_GOOD, secondGood);
    }

    @Test
    public void removeExistingGoodFromExistingTakenShelves() {
        Assert.assertEquals(INFO_FOR_REMOVING_GOOD, shop.removeGoods(SHELVES_FOR_FIRST_GOOD, firstGood));
        Assert.assertNull(shop.getShelves().get(SHELVES_FOR_FIRST_GOOD));
    }
}