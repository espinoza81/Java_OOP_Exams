package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {
    private final static String FARM_NAME = "Farm Ville";
    private final static int FARM_CAPACITY = 3;
    private final static int FARM_ANIMAL_COUNT = 2;
    private final static String ANIMAL_TYPE_FOR_REMOVE = "Dog";

    private Farm farm;
    private Farm farmFull;
    private Animal firstAnimal;

    @Before
    public void setUp(){
        farm = new Farm(FARM_NAME, FARM_CAPACITY);
        farmFull = new Farm(FARM_NAME, FARM_CAPACITY);

        firstAnimal = new Animal("Dog", 5.6);
        Animal secondAnimal = new Animal("Cat", 16.7);
        Animal thirdAnimal = new Animal("Parrot", 34.7);

        farm.add(firstAnimal);
        farm.add(secondAnimal);

        farmFull.add(firstAnimal);
        farmFull.add(secondAnimal);
        farmFull.add(thirdAnimal);
    }

    @Test(expected = NullPointerException.class)
    public void createFarmWhitNullName(){

        Farm testFarm = new Farm(null, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFarmWhitNegativeCapacity(){

        Farm testFarm = new Farm("Test Farm", -5);
    }

    @Test
    public void getAnimalCountInFarm() {

        Assert.assertEquals(FARM_ANIMAL_COUNT, farm.getCount());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addExistingAnimalInFarm() {

        farm.add(firstAnimal);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addAnimalInFullFarm() {
        Animal fourAnimal = new Animal("Ferret", 4.56);

        farmFull.add(fourAnimal);
    }

    @Test
    public void removeAnimalFromFarm() {

        Assert.assertTrue(farm.remove(ANIMAL_TYPE_FOR_REMOVE));
    }

    @Test
    public void getNameForFirstFarm(){

        Assert.assertEquals(FARM_NAME, farm.getName());
    }
}