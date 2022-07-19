package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimalTest {
    private static final double FIRST_ANIMAL_ENERGY = 5.6;
    private Animal firstAnimal;

    @Before
    public void setUp() {

        firstAnimal = new Animal("Dog", FIRST_ANIMAL_ENERGY);
    }

    @Test
    public void getAnimalEnergy(){
        Assert.assertEquals(FIRST_ANIMAL_ENERGY, firstAnimal.getEnergy(), 0);
    }
}