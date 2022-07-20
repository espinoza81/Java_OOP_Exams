package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
    private static final int AQUARIUM_CAPACITY = 3;
    private static final String FAKE_FISH_NAME = "Fake name";
    private static final String FIRST_FISH_NAME = "Pencho";
    private static final String SECOND_FISH_NAME = "Leta";
    private static final String THIRD_FISH_NAME = "Sisa";

    private Fish firstFish;
    private Fish secondFish;
    private Fish thirdFish;
    private Aquarium aquarium;


    @Before
    public void setUp() {
        firstFish = new Fish(FIRST_FISH_NAME);
        secondFish = new Fish(SECOND_FISH_NAME);
        thirdFish = new Fish(THIRD_FISH_NAME);
        aquarium = new Aquarium("aqua", AQUARIUM_CAPACITY);

        aquarium.add(firstFish);
        aquarium.add(secondFish);
        aquarium.add(thirdFish);
        aquarium.remove(THIRD_FISH_NAME);
        aquarium.add(thirdFish);
    }

    @Test (expected = NullPointerException.class)
    public void createAquariumWithNullName() {

        Aquarium fakeAquarium = new Aquarium(null, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void createAquariumWithNegativeCapacity() {

        Aquarium fakeAquarium = new Aquarium("Fake Aquarium", -2);
    }

    @Test
    public void getCountOfFishInAquarium() {

        Assert.assertEquals(AQUARIUM_CAPACITY, aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addFishInFullAquarium() {

        aquarium.add(firstFish);
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFishWithNameNonExist() {

        aquarium.remove(FAKE_FISH_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sellFishWithNameNonExist() {

        aquarium.sellFish(FAKE_FISH_NAME);
    }

    @Test
    public void sellFishWithRightName() {

        Fish sellFish = aquarium.sellFish(FIRST_FISH_NAME);

        Assert.assertFalse(sellFish.isAvailable());
    }
}