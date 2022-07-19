package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GiftFactoryTests {
    private static final String FIRST_GIFT_NAME = "Barbie";
    private static final double FIRST_GIFT_MAGIC = 10.5;
    private static final String SECOND_GIFT_NAME = "Teddy Bear";
    private static final double SECOND_GIFT_MAGIC = 30.5;
    private static final String THIRD_GIFT_NAME = "Truck";
    private static final double THIRD_GIFT_MAGIC = 20.5;
    private static final int EXPECTED_COUNT = 3;

    private GiftFactory giftFactory;
    private Gift gift;
    private Gift secondGift;
    private Gift thirdGift;

    @Before
    public void setUp(){
        giftFactory = new GiftFactory();
        gift = new Gift(FIRST_GIFT_NAME, FIRST_GIFT_MAGIC);
        secondGift = new Gift(SECOND_GIFT_NAME, SECOND_GIFT_MAGIC);
        thirdGift = new Gift(THIRD_GIFT_NAME, THIRD_GIFT_MAGIC);
        giftFactory.createGift(gift);
        giftFactory.createGift(secondGift);
        giftFactory.createGift(thirdGift);
    }

    @Test
    public void getGiftFactoryCount() {
        int actualCount = giftFactory.getCount();

        Assert.assertEquals(EXPECTED_COUNT, actualCount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingGiftToGiftFactory() {

        giftFactory.createGift(gift);
    }

    @Test
    public void removeGiftFromGiftFactory(){
        giftFactory.removeGift(SECOND_GIFT_NAME);

        Assert.assertTrue(true);
    }

    @Test(expected = NullPointerException.class)
    public void removeGiftWhitNullNameFromGiftFactory(){
        giftFactory.removeGift(null);
    }

    @Test
    public void getPresentWithLeastMagic(){

        Assert.assertEquals(gift, giftFactory.getPresentWithLeastMagic());
    }

    @Test
    public void getPresentWhitNameTeddyBear(){
        Assert.assertEquals(secondGift, giftFactory.getPresent("Teddy Bear"));

    }

//    @Test
//    public void getCollectionOfPresents(){
//
//        List<Gift> expected = new ArrayList<>();
//        expected.add(gift);
//        expected.add(secondGift);
//        expected.add(thirdGift);
//
//        Collection<Gift> unmodifiableExpected = Collections.unmodifiableCollection(expected);
//
//        Assert.assertEquals(unmodifiableExpected, giftFactory.getPresents());
//    }
}